package la.udd.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

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

@RestController
@RequestMapping(value = "/bookUnit")
@CrossOrigin(origins = "http://localhost:4200")
public class BookUnitController {

	@Autowired
	private BookUnitService bookUnitService;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	private BookRejectedUnitService bookRejectedUnitService;
	
	@GetMapping(path = "/publishBook/{idBook}", produces = "application/json")
	public ResponseEntity<String> publishBookUnit(@PathVariable("idBook") Long id) {
		System.out.println("In save book unit");
		Book book = bookRepository.findOneById(id);
		BookUnit bookUnit = new BookUnit(book.getId(),book.getFilename(), book.getTitle(), book.getWriter().getFirstName()+" "+book.getWriter().getLastName(),
				book.getGenre(), book.getIsOpenAccess());
		
		bookUnitService.add(bookUnit);
		book.setStatus("Objavlejna");
		bookRepository.save(book);
		
		return new ResponseEntity<String>("Successfully published!", HttpStatus.OK);
	}
	
	
	@PostMapping("/saveBookUnit")
	public ResponseEntity<String> saveBookUnit(@RequestBody BookUnit bookUnit) {
		
		System.out.println("In save book unit");
		bookUnitService.add(bookUnit);
		
		return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
	}
	
	@GetMapping("/getBookUnits")
	public Iterable<BookUnit> getBookUnits(){
		System.out.println("In get book units");
		
		return bookUnitService.findAll();
	}
	
	@GetMapping("/getBookUnitsRejected")
	public Iterable<BookRejectedUnit> getBookUnitsRejected(){
		System.out.println("In get book units rejected");
		return bookRejectedUnitService.findAll();
	}
    
	/*@GetMapping("/reindex")
    public ResponseEntity<String> index() throws IOException {
        File dataDir = getResourceFilePath("target\\classes\\files");
		long start = new Date().getTime();
        int numIndexed = bookUnitService.index(dataDir);
        long end = new Date().getTime();
        String text = "Indexing " + numIndexed + " files took "
            + (end - start) + " milliseconds";
        return new ResponseEntity<String>(text, HttpStatus.OK);
    }
    
    private File getResourceFilePath(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        System.out.println("Url je: " + url.toString());
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }*/
}
