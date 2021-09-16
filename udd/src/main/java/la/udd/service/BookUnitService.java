package la.udd.service;

import la.udd.elastic.BookUnit;

public interface BookUnitService {
	public boolean add(BookUnit b);
	public Iterable<BookUnit> findAll();
	public boolean update(BookUnit b);
}
