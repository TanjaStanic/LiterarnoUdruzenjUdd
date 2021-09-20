package la.udd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

import la.udd.elastic.BookUnit;
import la.udd.repository.BookUnitRepository;
import la.udd.service.BookUnitService;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;


@Service
public class BookUnitServiceImpl implements BookUnitService {

	
	@Autowired
	private BookUnitRepository bookUnitRepository;
	
	public BookUnitServiceImpl() {}
	
	@Override
	public boolean add(BookUnit b) {
		//b = bookUnitRepository.index(b);
		b =  bookUnitRepository.save(b);
		if (b!=null) {
			
			return true;
		}
		else
			return false;
	}

	@Override
	public Iterable<BookUnit> findAll() {
		return bookUnitRepository.findAll();
	}

	@Override
	public int index(File file) {
		BookUnit b = getBookUnit(file);
        int retVal = 0;
        try {
            File[] files;
            if (file.isDirectory()) {
                files = file.listFiles();
            } else {
                files = new File[1];
                files[0] = file;
            }
            for (File newFile : files) {
                if (newFile.isFile()) {
                    BookUnit book = b;
                    book.setTitle(b.getTitle());
                    book.setKeywords(b.getKeywords());
                    book.setFilename(b.getFilename());
                    book.setWriter(b.getWriter());
                    book.setGenre(b.getGenre());
                    book.setContent(b.getContent());
                    if (add(book)) {
                        retVal++;
                    }
                } else if (newFile.isDirectory()) {
                    retVal += index(newFile);
                }
            }
            System.out.println("Indexed");
        } catch (Exception e) {
            System.out.println("NOT Indexed");
        }
        return retVal;
    }
	
	public BookUnit getBookUnit(File file) {
		BookUnit book = new BookUnit();
        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            String text = getText(parser);
            book.setContent(text);

            PDDocument pdf = parser.getPDDocument();
            PDDocumentInformation info = pdf.getDocumentInformation();

            String title = "" + info.getTitle();
            book.setTitle(title);

            String keywords = "" + info.getKeywords();
            book.setKeywords(keywords);

            book.setFilename(file.getCanonicalPath());

            pdf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return book;
    }
	
    public String getText(File file) {
        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getText(PDFParser parser) {
        try {
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
