package la.udd.controller;
import org.springframework.expression.ParseException;

import java.util.ArrayList;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import la.udd.dto.BasicQueryResponseDTO;
import la.udd.model.Book;
import la.udd.repository.BookRepository;

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
	
	
	@PostMapping(value="/basic/{field}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity basicQuery(@PathVariable String field, @RequestBody String query) throws ParseException {
        System.out.println("Query: " + query);
        
        ArrayList<BasicQueryResponseDTO> retVal = new ArrayList<>();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        highlightBuilder.highlightQuery(QueryBuilders.queryStringQuery(query));

        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(query);
        if (!field.equals("all")) {
        	 queryStringQueryBuilder.field(field);
        }
        
        QueryStringQueryBuilder app = new QueryStringQueryBuilder("true");
        app.field("approved");
       
        queryStringQueryBuilder.analyzer("serbian");
        boolQuery.must(queryStringQueryBuilder);
        boolQuery.must(app);
        SearchRequestBuilder request = nodeClient.prepareSearch("articleindexnova")
                .setQuery(boolQuery)
                .setSearchType(SearchType.DEFAULT)
                .highlighter(highlightBuilder);
        SearchResponse response = request.execute().actionGet();
        System.out.println(response.toString());
        
        return null;
	}
	
	@GetMapping (path="/findBetaReaderByGenre")
	public ResponseEntity<?> findBetaReaderByGenre(@PathVariable Long id) {
		
		Book book = bookRepository.findOneById(id);

		
		return null;
	}
	

}
