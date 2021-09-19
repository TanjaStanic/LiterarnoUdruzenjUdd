package la.udd.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import la.udd.elastic.BetaReader;

public interface BetaReaderRepository extends ElasticsearchRepository<BetaReader, Long>{

	
}
