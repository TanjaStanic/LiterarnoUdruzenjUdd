package la.udd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import la.udd.elastic.BetaReader;
import la.udd.repository.BetaReaderRepository;
import la.udd.service.BetaReaderService;
import la.udd.service.BookUnitService;

@Service
public class BetaReaderServiceImpl implements BetaReaderService{
	@Autowired
	private BetaReaderRepository betaReaderRepository;
	
	public BetaReaderServiceImpl() {}

	@Override
	public boolean add(BetaReader b) {
		b = betaReaderRepository.save(b);
		if (b!=null)
			return true;
		else
			return false;
	}

	@Override
	public Iterable<BetaReader> findAll() {
		return betaReaderRepository.findAll();
	}

	@Override
	public boolean update(BetaReader b) {
		b = betaReaderRepository.save(b);
		if(b!=null)
			return true;
		else
			return false;
	}
}
