package la.udd.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import la.udd.elastic.BookUnit;
import org.springframework.stereotype.Repository;

@Repository
public interface BookUnitRepository extends ElasticsearchRepository<BookUnit, Long>{


}
