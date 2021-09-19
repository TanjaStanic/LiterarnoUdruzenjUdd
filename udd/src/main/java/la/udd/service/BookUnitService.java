package la.udd.service;

import java.io.File;

import la.udd.elastic.BookUnit;

public interface BookUnitService {
	public boolean add(BookUnit b);
	public Iterable<BookUnit> findAll();
	public boolean update(BookUnit b);
	public int index(File file);
}
