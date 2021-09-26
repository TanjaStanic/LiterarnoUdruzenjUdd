package la.udd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;

import la.udd.dto.BetaReaderDTO;
import la.udd.elastic.BetaReader;
import la.udd.model.Book;
import la.udd.model.User;
import la.udd.repository.BookRepository;
import la.udd.service.impl.BetaReaderServiceImpl;
import la.udd.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/betaReader")
@CrossOrigin(origins = "http://localhost:4200")
public class BetaReaderController {
	
	
	@Autowired
	private BetaReaderServiceImpl BetaReaderService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private Client nodeClient;
	
	@GetMapping("/deleteAll")
	public ResponseEntity<String> deleteAll(){
		System.out.println("In delete book units ");
		BetaReaderService.deleteAll();
		return new ResponseEntity<String>("Successfully deleted!", HttpStatus.OK);
	}
	
	@GetMapping(value = "/reindex", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> indexBetaReaders() throws IOException{
		
		List<User> users = new ArrayList<User>();
		users = userService.getBetaReaders();
		BetaReader b;
		for (User u : users) {
			b = new BetaReader(u.getId(), u.getFirstName(), u.getLastName(),
					u.getEmail(), u.getUsername(), u.getCity(),
					u.getCountry(), u.getGenre(), 
					new GeoPoint(u.getLatitude(), u.getLongitude()));
			BetaReaderService.add(b);
		}
		return new ResponseEntity<>("Successfully indexed!", HttpStatus.OK);

	}


	@GetMapping(value = "/getBetaReaders/{idBook}/{includeLocation}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBetaReaders(@PathVariable Long idBook, @PathVariable Boolean includeLocation) throws IOException{
		
		System.out.println("Id book " + idBook + ". IncludeLocation: " + includeLocation);
		
		Book book = bookRepository.findOneById(idBook);
		List<BetaReaderDTO> retVal = new ArrayList<>();
		
		//Pretrazi po zanru
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		boolQuery.must(QueryBuilders.matchQuery("genre", book.getGenre()));
		
        //pretrazi i geo ako je true
      
        if(includeLocation){
        	  System.out.println(" in include location");
        	boolQuery.mustNot(QueryBuilders.geoDistanceQuery("location").
        			point(book.getWriter().getLatitude(), book.getWriter().getLongitude()).distance(100, DistanceUnit.KILOMETERS));
          
        }
        SearchRequestBuilder request = nodeClient.prepareSearch("betareaderlibrary")
                .setQuery(boolQuery)
                .setSearchType(SearchType.DEFAULT);
        System.out.println("Request: " + request);
        
        //SearchResponse response = request.get();
        SearchResponse response = request.execute().actionGet();
        System.out.println("Response: " + response.toString());
        
        //Get beta readers from hit
        for(SearchHit hit : response.getHits().getHits()) {
            Gson gson = new Gson();
            BetaReader br = gson.fromJson(hit.getSourceAsString(), BetaReader.class);
            BetaReaderDTO bDTO = new BetaReaderDTO(br.getId(), br.getFirstName(), br.getLastName(), br.getEmail(),
            										br.getUsername(), br.getCity(), br.getCountry(), br.getGenre());
            
            if (!retVal.contains(bDTO)) {
            	retVal.add(bDTO);
            }
        }
    
        	return new ResponseEntity<>(retVal, HttpStatus.OK);
        
		
	}
	
	
}
