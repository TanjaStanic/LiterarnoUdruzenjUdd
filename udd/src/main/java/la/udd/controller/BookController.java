package la.udd.controller;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import la.udd.model.Book;
import la.udd.repository.BookRepository;

@RestController
@RequestMapping(value = "/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@GetMapping(path = "/journals", produces = "application/json")
    public ResponseEntity<?> getAllActivatedBooks() {
			List<Book> books = new ArrayList<Book>(); 
			List<Book> retVal = new ArrayList<Book>(); 
			books = bookRepository.findAll();
			if (!books.isEmpty()) {
				for (Book b : books) {
					if (b.getActivated()) {
						retVal.add(b);
					}
				}
				return new ResponseEntity<>(retVal,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.OK);
			}
	}
}
