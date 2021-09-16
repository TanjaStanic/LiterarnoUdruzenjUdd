package la.udd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import la.udd.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
		Book findOneById(Long id);
		Book findOneByIsbn(String isbn);
		

}
