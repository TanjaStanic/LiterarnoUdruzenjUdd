package la.udd.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;
import la.udd.elastic.BookRejectedUnit;
import la.udd.elastic.BookUnit;
import la.udd.model.Book;
import la.udd.repository.BookRepository;
import la.udd.service.BookRejectedUnitService;
import la.udd.service.BookUnitService;
import la.udd.service.impl.BookServiceImpl;

@RestController
@RequestMapping(value = "/bookUnit")
@CrossOrigin(origins = "http://localhost:4200")
public class BookUnitController {

	@Autowired
	private BookUnitService bookUnitService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookServiceImpl bookService;
	
	
	
	@GetMapping(path = "/publishBook/{idBook}", produces = "application/json")
	public ResponseEntity<String> publishBookUnit(@PathVariable("idBook") Long id) throws IOException {
		System.out.println("In save book unit");
		Book book = bookRepository.findOneById(id);
		String content = bookService.getContent(book.getFilename());
		BookUnit bookUnit = new BookUnit(book.getId(),book.getFilename(), book.getTitle(), book.getWriter().getFirstName()+" "+book.getWriter().getLastName(),
				book.getGenre(), book.getIsOpenAccess(), content);
		
		bookUnitService.add(bookUnit);
		book.setStatus("Objavljena");
		bookRepository.save(book);
		
		return new ResponseEntity<String>("Successfully published!", HttpStatus.OK);
	}
	
	
	@PostMapping("/saveBookUnit")
	public ResponseEntity<String> saveBookUnit(@RequestBody BookUnit bookUnit) {
		
		System.out.println("In save book unit");
		bookUnitService.add(bookUnit);
		
		return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
	}
	
	@PostMapping("/saveBookUnits")
	public ResponseEntity<String> saveBookUnits(@RequestBody List<BookUnit> bookUnits) {
		
		System.out.println("In save book units!");
		for (BookUnit b : bookUnits) {
			bookUnitService.add(b);
		}
		
		
		return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
	}
	
	@GetMapping("/getBookUnits")
	public Iterable<BookUnit> getBookUnits(){
		System.out.println("In get book units");
		
		return bookUnitService.findAll();
	}
	

    
	@GetMapping("/reindex")
    public ResponseEntity<String> index() throws IOException {
        List<Book> books = bookService.getAllPublished();
        
        for (Book b : books) {
        	String content = bookService.getContent(b.getFilename());
        	
        	String writer = b.getWriter().getFirstName() + " " + b.getWriter().getLastName();
        	
        	BookUnit bu = new BookUnit(b.getId(), b.getFilename(),
        			b.getTitle(), writer, b.getGenre(), b.getIsOpenAccess(), content);
        			
        	bookUnitService.add(bu);
        }
		
        return new ResponseEntity<>("Indexed", HttpStatus.OK);

    }
    
    @GetMapping("/deleteAll")
	public ResponseEntity<String> deleteAll(){
		System.out.println("In delete book units ");
		bookUnitService.deleteAll();
		return new ResponseEntity<String>("Successfully deleted!", HttpStatus.OK);
	}
}
