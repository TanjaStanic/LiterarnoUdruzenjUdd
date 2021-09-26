package la.udd.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import la.udd.model.Book;
import la.udd.repository.BookRepository;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.io.RandomAccessFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
public class BookServiceImpl {
	
	private String files = "C:\\Users\\Korisnik\\git\\LiterarnoUdruzenjeUdd\\udd\\files\\";
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllPublished(){
		List<Book> books = new ArrayList<Book>();
		List<Book> retVal = new ArrayList<Book>();
		books = bookRepository.findAll();
		
		for (Book b : books) {
			if (b.getStatus().equals("Objavljena")) {
				retVal.add(b);
			}
		}
		return retVal;
	}
	
	public String saveFile(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(files + file.getOriginalFilename());
		
		System.out.println(path.toAbsolutePath());

		Files.write(path, bytes);
		return path.toAbsolutePath().toString();
	}
	
	public Resource loadFile(String fileName) {
        try {
        	Path path = Paths.get(files + fileName).normalize();
     		System.out.println(path.toAbsolutePath());
     		
     		System.out.println("putanja loadFile" + path);
     		Resource resource = new UrlResource(path.toUri());
        	
        		path = Paths.get(files + fileName+".pdf").normalize();
        	

            Resource resource2 = new UrlResource(path.toUri());
            
            System.out.println("putanja2 loadFile" + path);
            if(resource.exists()) {
            	System.out.println(" RESOURCE " + resource);
                return resource;
            }
            
            if(resource2.exists()) {
            	System.out.println(" RESOURCE2 " + resource2);
                return resource2;
            }
            
        } catch (MalformedURLException ex) {
           
        	System.out.println("File does not exists!");
        	
        }
        
        return null;
    }
	
	public String getContent(String url) throws IOException {
		
		url = "files\\"+ url;
		PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        System.out.println("TEST " + url);
        System.out.println("TEST2 " + files + url);
      //String test  = files+heading+".pdf";
        Path path = Paths.get(url).normalize();
        
        String parsedContent = "";
		try {
			Resource resource = new UrlResource(path.toUri());
			File file = resource.getFile();
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            PDFParser parser = new PDFParser(randomAccessFile);
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(pdDoc.getNumberOfPages());
            parsedContent = pdfStripper.getText(pdDoc);
            pdDoc.close();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return parsedContent;
	}
	
}
