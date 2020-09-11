package pack.service;

import org.springframework.stereotype.Service;
import pack.entities.Book;
import pack.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

        private BookRepository repository;
        private AuthorService authorService;

    public BookService(BookRepository repository, AuthorService authorService) {
        this.repository = repository;
        this.authorService = authorService;
    }

    public Book create(Book book, Long authorId) throws Exception {
        return authorService.findById(authorId).map(it -> {
            book.setAuthor(it);
            return repository.save(book);
        }).orElseThrow(() ->
                new Exception("Author by the ID: "+authorId+" does not exists"));
    }
//TODO
    public Book update(Book book) throws Exception {
        if (book.getId() == null)
            throw new Exception("Book id cannot be null in order to update;");
        try {
            return repository.save(book);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public List<Book> findAll() {
        return (List<Book>) repository.findAll();
    }

    public Book deleteById(Long id) throws Exception {
        return findById(id).map(it -> {
            try {
                repository.deleteById(id);
            } catch (Exception e){
                e.printStackTrace();
            }
            return it;
        }).orElseThrow(() ->
                new Exception("Book With ID: " + id + " Is Not Exist"));
    }

}
