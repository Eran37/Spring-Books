package pack.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pack.entities.Author;
import pack.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorDao {

    private AuthorRepository repository;

        @Autowired
        public AuthorDao(AuthorRepository repository){this.repository = repository;}

    public Author save(Author author) throws Exception {
        try {
            return repository.save(author);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Author>findById(Long id) {
        return repository.findById(id);
    }

    public List<Author> findAll() {
        return (List<Author>) repository.findAll();
    }

    //Returns an Optional(). if Author is inside, we can return it. else we get null;
    //.map() is iterable;
    public Author deleteById (Long id) throws Exception {
        return findById(id).map(it -> {
            repository.delete(it);
            //if Author exists inside the Optional object and I want it, I need to return 'it';
            return it;
        }).orElseThrow(() -> new Exception("Author with the id: "+id+" have not found." ));
    }








}
