package la.udd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import la.udd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	User findByUsername(String username);
	User findOneById(Long id);
	List<User> findAll();
	
}
