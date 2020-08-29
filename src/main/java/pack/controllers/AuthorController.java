package pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    //TODO - To fix the recursive problem of author/byId
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

}
