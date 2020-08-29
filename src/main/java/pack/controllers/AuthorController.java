package pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.entities.Author;
import pack.service.AuthorService;

import java.util.Optional;

//  http://localhost:7070/author

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    //  http://localhost:7070/author/byId/1
    @GetMapping("/byId/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Author> author = service.findById(id);
        return author.isPresent() ?

                ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(author.get())
                :
                ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Author by the id " + id + " does not exist");
    }

    //  http://localhost:7070/author/create
    @PostMapping("/create")
    public ResponseEntity create (@RequestBody Author author) {
        try {
            Author afterSave = service.save(author);

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

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Author author) {
        if (author.getId() == null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("In order to update must provide id");
        }
        return create(author);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            Author deleted = service.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(deleted);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
    }

}
