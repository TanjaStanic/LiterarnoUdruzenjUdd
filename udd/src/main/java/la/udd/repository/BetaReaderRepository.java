package la.udd.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import la.udd.elastic.BetaReader;
@Repository
public interface BetaReaderRepository extends ElasticsearchRepository<BetaReader, Long>{

	
}
