package la.udd.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import la.udd.elastic.BookRejectedUnit;
@Repository
public interface BookRejectedUnitRepository extends ElasticsearchRepository<BookRejectedUnit, Long>{


}
