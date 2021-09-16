package la.udd.service;

import la.udd.elastic.BookRejectedUnit;

public interface BookRejectedUnitService {
	public boolean add(BookRejectedUnit b);
	public Iterable<BookRejectedUnit> findAll();
	public boolean update(BookRejectedUnit b);
}
