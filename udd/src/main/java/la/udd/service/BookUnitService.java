package la.udd.service;

import java.io.File;

import org.springframework.stereotype.Service;

import la.udd.elastic.BookUnit;
@Service
public interface BookUnitService {
	public boolean add(BookUnit b);
	public Iterable<BookUnit> findAll();
	public int index(File file);
}
