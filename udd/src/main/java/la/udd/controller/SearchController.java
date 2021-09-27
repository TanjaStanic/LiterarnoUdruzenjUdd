package la.udd.controller;
import org.springframework.expression.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.elasticsearch.search.SearchHit;

import la.udd.dto.AdvancedQueryDTO;
import la.udd.dto.QueryResponseDTO;
import la.udd.elastic.BookUnit;
import la.udd.model.Book;
import la.udd.repository.BookRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

	
	@Autowired
	private Client nodeClient;
	
	@Autowired
	private BookRepository bookRepository;
	
	private HighlightBuilder highlightBuilder = new HighlightBuilder()
            .field("title", 50)
            .field("writer", 50)
            .field("content", 50)
            .field("genre", 50);
	
	private HighlightBuilder highlightBuilderAdv = new HighlightBuilder()
            .field("title", 50)
            .field("writer", 50)
            .field("content", 50)
            .field("genre", 50);
	
	
	@PostMapping(value="/basic/{field}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> basicQuery(@PathVariable String field, @RequestBody String query) throws ParseException {
		System.out.println("field: " + field);
		System.out.println("Query: " + query);
        
        ArrayList<QueryResponseDTO> retVal = new ArrayList<>();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        highlightBuilder.highlightQuery(QueryBuilders.queryStringQuery(query));

        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(query);
        if (!field.equals("all")) {
        	 queryStringQueryBuilder.field(field);
        }
       
        queryStringQueryBuilder.analyzer("serbian");
        boolQuery.must(queryStringQueryBuilder);
        SearchRequestBuilder request = nodeClient.prepareSearch("booklibrary")
                .setQuery(boolQuery)
                .setSearchType(SearchType.DEFAULT)
                .highlighter(highlightBuilder);
        SearchResponse response = request.execute().actionGet();
        System.out.println(response.toString());
        retVal = getResponse(response);
        
        return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@PostMapping(value="/advanced", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> advancedQuery(@RequestBody List<AdvancedQueryDTO> listQuery) throws ParseException {
		ArrayList<QueryResponseDTO> retVal = new ArrayList<>();

		for (AdvancedQueryDTO advanced : listQuery) {
        	System.out.println(advanced.getField() + " " +advanced.getOperation() + " " + advanced.isPhrase() + " " + advanced.getQuery());
        }
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //boolQuery.must(QueryBuilders.matchQuery("approved", "true"));
		
		for (AdvancedQueryDTO advanced : listQuery) {
        	if (advanced.getQuery()!=null && !advanced.getQuery().isEmpty()) {	//da li je ista upisao u query
        		//OPERATOR AND
        		if (advanced.getOperation().equals("AND")) {					
        			//jeste fraza
        			if (advanced.isPhrase()) {		        					
        				
        				if (advanced.getField().equals("all")) {
        					boolQuery.must(QueryBuilders.multiMatchQuery(advanced.getQuery(), "title", "writer", "content",
                                    "genre").type("phrase").analyzer("serbian"));
        				}else {
        					boolQuery.must(QueryBuilders.matchPhraseQuery(advanced.getField(), advanced.getQuery().toLowerCase()).analyzer("serbian"));
        				}
        				
        			//nnije fraza	
        			}else {
        				
        				if (advanced.getField().equals("all")) {
        					boolQuery.must(QueryBuilders.multiMatchQuery(advanced.getQuery(), "title", "writer", "content",
                                    "genre").analyzer("serbian"));
        				}else {
        					boolQuery.must(QueryBuilders.matchQuery(advanced.getField(), advanced.getQuery().toLowerCase()).analyzer("serbian"));
        				}
        				
        			}
        			//OPERATOR OR
        		}else if (advanced.getOperation().equals("OR")) {
        			if (advanced.isPhrase()) {		
        				
        				if (advanced.getField().equals("all")) {
        					boolQuery.should(QueryBuilders.multiMatchQuery(advanced.getQuery(),"heading", "keyterms", "abs",
                                    "mainSC", "magazine", "content", "author", "coauthor").type("phrase").analyzer("serbian"));
        				}else {
        					boolQuery.should(QueryBuilders.matchPhraseQuery(advanced.getField(), advanced.getQuery().toLowerCase()).analyzer("serbian"));
        				}
        				
        			}else {
        				
        				if (advanced.getField().equals("all")) {
        					boolQuery.should(QueryBuilders.multiMatchQuery(advanced.getQuery(), "title", "writer", "content",
                                    "genre").analyzer("serbian"));
        				}else {
        					boolQuery.should(QueryBuilders.matchQuery(advanced.getField(), advanced.getQuery().toLowerCase()).analyzer("serbian"));
        				}
        			}
        		}
        	}
        }
		
		
		SearchRequestBuilder request = nodeClient.prepareSearch("booklibrary")
                .setQuery(boolQuery)
                .setSearchType(SearchType.DEFAULT)
                .highlighter(highlightBuilderAdv);
        System.out.println(request);
        
        SearchResponse response = request.execute().actionGet();
        
        System.out.println(response.toString());
        retVal = getResponse(response);
        System.out.println(retVal.size());

        return new ResponseEntity<>(retVal, HttpStatus.OK);
	
		
	}
	
	 private ArrayList<QueryResponseDTO> getResponse(SearchResponse response){
	        ArrayList<QueryResponseDTO> retVal = new ArrayList<>();
	        for(SearchHit hit : response.getHits().getHits()) {
	            Gson gson = new Gson();
	            QueryResponseDTO basicQueryResponseDTO = new QueryResponseDTO();

	            BookUnit object = gson.fromJson(hit.getSourceAsString(), BookUnit.class);
	            basicQueryResponseDTO.setBookUnit(object);

	            String allHighlights = "...";

	            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
	            for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()){
	               
	                String value = Arrays.toString(entry.getValue().fragments());
	                
	                //substring zbog uglastih zagrada fragmenata na pocetku i kraju
	                allHighlights+=value.substring(1, value.length()-1);
	                allHighlights+="...";
	            }

	            allHighlights = allHighlights.replace("<em>", "<b>");
	            allHighlights = allHighlights.replace("</em>", "</b>");
	            System.out.println(allHighlights);
	            basicQueryResponseDTO.setHighlights(allHighlights);
	            retVal.add(basicQueryResponseDTO);
	        }
	        return retVal;
	    }	


}
