package la.udd.service;

import la.udd.elastic.BetaReader;

public interface BetaReaderService {
	public boolean add(BetaReader b);
	public Iterable<BetaReader> findAll();
	public boolean update(BetaReader b);
}
