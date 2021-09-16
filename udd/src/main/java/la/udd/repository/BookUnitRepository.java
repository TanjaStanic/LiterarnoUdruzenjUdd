package la.udd.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import la.udd.elastic.BookUnit;

public interface BookUnitRepository extends ElasticsearchRepository<BookUnit, Long>{

	BookUnit index(BookUnit b);

}
