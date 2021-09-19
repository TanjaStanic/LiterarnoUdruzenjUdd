package la.udd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import la.udd.elastic.BookRejectedUnit;
import la.udd.repository.BookRejectedUnitRepository;
import la.udd.service.BookRejectedUnitService;

@Service
public class BookRejectedUnitServiceImpl implements BookRejectedUnitService {

	@Autowired
	private BookRejectedUnitRepository bookUnitRepository;
	
	public BookRejectedUnitServiceImpl() {}
	
	@Override
	public boolean add(BookRejectedUnit b) {
		b =  bookUnitRepository.save(b);
		if (b!=null) {
			return true;
		}
		else
			return false;
	}

	@Override
	public Iterable<BookRejectedUnit> findAll() {
		return bookUnitRepository.findAll();

	}

	@Override
	public boolean update(BookRejectedUnit b) {
		b = bookUnitRepository.save(b);
		if(b!=null)
			return true;
		else
			return false;
	}

}
