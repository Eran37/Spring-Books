package pack.service;

import org.springframework.stereotype.Service;
import pack.entities.Book;
import pack.repository.BookRepository;

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
}
