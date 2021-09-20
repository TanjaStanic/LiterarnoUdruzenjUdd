package la.udd.service;

import org.springframework.stereotype.Service;

import la.udd.elastic.BetaReader;

@Service
public interface BetaReaderService {
	public boolean add(BetaReader b);
	public Iterable<BetaReader> findAll();
}
