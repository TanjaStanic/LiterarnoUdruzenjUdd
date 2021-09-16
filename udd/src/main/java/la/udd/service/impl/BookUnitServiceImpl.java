package la.udd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import la.udd.elastic.BookUnit;
import la.udd.repository.BookUnitRepository;
import la.udd.service.BookUnitService;

@Service
public class BookUnitServiceImpl implements BookUnitService {

	
	@Autowired
	private BookUnitRepository bookUnitRepository;
	
	public BookUnitServiceImpl() {}
	
	@Override
	public boolean add(BookUnit b) {
		b =  bookUnitRepository.index(b);
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
	public boolean update(BookUnit b) {
		b = bookUnitRepository.save(b);
		if(b!=null)
			return true;
		else
			return false;
	}

	
}
