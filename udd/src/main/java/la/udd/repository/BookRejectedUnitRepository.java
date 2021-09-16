package la.udd.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import la.udd.elastic.BookRejectedUnit;

public interface BookRejectedUnitRepository extends ElasticsearchRepository<BookRejectedUnit, Long>{

	BookRejectedUnit index(BookRejectedUnit b);

}
