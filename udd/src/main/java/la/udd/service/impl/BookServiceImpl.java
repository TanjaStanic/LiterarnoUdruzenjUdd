package la.udd.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
public class BookServiceImpl {
	
	private String files = "C:\\Users\\Korisnik\\git\\LiterarnoUdruzenjeUdd\\udd\\files\\";
	
	public String saveFile(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(files + file.getOriginalFilename());
		
		System.out.println(path.toAbsolutePath());
		System.out.println("1");

		Files.write(path, bytes);
		System.out.println("2");
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
}
