package la.udd.service;

import org.springframework.stereotype.Service;

import la.udd.elastic.BookRejectedUnit;
@Service
public interface BookRejectedUnitService {
	public boolean add(BookRejectedUnit b);
	public Iterable<BookRejectedUnit> findAll();
}
