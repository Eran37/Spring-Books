package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
