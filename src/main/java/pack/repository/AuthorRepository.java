package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.entities.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
