package la.udd.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import la.udd.model.Book;
import la.udd.model.User;
import la.udd.repository.BookRepository;
import la.udd.repository.UserRepository;
import la.udd.service.impl.BookServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookServiceImpl bookServiceImpl;
	
	@Autowired
	UserRepository userRepository;;
	
	@GetMapping(path = "/getAllBooks/{idWriter}", produces = "application/json")
	public ResponseEntity<?> getAllBooksWriter(@PathVariable("idWriter") Long id){
		List<Book> books = new ArrayList<Book>();
		books = bookRepository.findAllByWriterId(id);
		
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	@GetMapping(path = "/getAllBooksEditor/{idEditor}", produces = "application/json")
	public ResponseEntity<?> getAllBooksEditor(@PathVariable("idEditor") Long id){
		List<Book> books = new ArrayList<Book>();
		books = bookRepository.findAllByEditorId(id);
		
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@PostMapping(path = "/addBookToWriter/{idWriter}", produces = "application/json")
	public ResponseEntity<?> addBookToWriter(@PathVariable("idWriter") Long id, @RequestBody Book book){
		User writer = userRepository.findOneById(id);
		User editor = userRepository.findOneById((long)2);
		book.setActivated(false);
		book.setWriter(writer);
		book.setEditor(editor);
		book.setStatus("Na cekanju");
		
		book = bookRepository.save(book);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/getAllActivatedBooks", produces = "application/json")
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
	
	@GetMapping(path="/activateBook/{idBook}", produces = "application/json")
	public ResponseEntity<?> activateBook(@PathVariable("idBook") Long id){
		Book book = bookRepository.findOneById(id);
		book.setActivated(true);
		book.setStatus("Odobrena");
		book = bookRepository.save(book);
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@GetMapping(path="/rejectBook/{idBook}", produces = "application/json")
	public ResponseEntity<?> rejectBook(@PathVariable("idBook") Long id){
		Book book = bookRepository.findOneById(id);
		book.setActivated(true);
		book.setStatus("Odbijena");
		book = bookRepository.save(book);
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping(path="/uploadFile/{idBook}", produces = "application/json")
	public ResponseEntity<?> uploadFile(@PathVariable("idBook") Long id, @RequestParam("file") MultipartFile file) {
		
		System.out.print("in upload file");
		System.out.print("Name, content "+ file.getName() + file.getOriginalFilename());
		Book book = bookRepository.findOneById(id);
		book.setFilename(file.getOriginalFilename());
		book = bookRepository.save(book);
		
		String returnValue ="";
		try {
			returnValue = bookServiceImpl.saveFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(returnValue, HttpStatus.OK);

	}
	
	@GetMapping(value = "/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id")String name, HttpServletRequest request) throws IOException {
		System.out.println("name "+name);
		Resource resource = bookServiceImpl.loadFile(name);
        
        String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath() + ".pdf");
		} catch (IOException ex) {
			System.out.println("Could not determine file type.");
		}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.LOCATION, resource.getURI().toString())
				.body(resource);
	}
	
}
