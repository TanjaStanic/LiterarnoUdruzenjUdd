package la.udd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import la.udd.model.Book;
import la.udd.model.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
		Book findOneById(Long id);
		Book findOneByIsbn(String isbn);
		List<Book> findAllByWriterId(Long id);
		List<Book> findAllByEditorId(Long id);

}
