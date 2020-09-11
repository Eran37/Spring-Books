package pack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.entities.Book;
import pack.entities.BookAdditionDto;
import pack.service.BookService;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    //Ctor
    public BookController(BookService service) { this.service = service; }

    @GetMapping("/byId/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        Optional<Book> book = service.findById(id);
        return book.isPresent() ?
                ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(book.get())
                :
                ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("The book with the id "+id+"have not found");
    }

    @PostMapping("/create")
    //(Created a new class that contains: authorId & book -> BookAdditionDto(**DTO == DataTransferObject**))
    public ResponseEntity create(@RequestBody BookAdditionDto dto) {
        try {
            Book afterSave = service.create(dto.getBook(), dto.getAuthorId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(afterSave);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
    }
}
