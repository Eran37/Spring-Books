package pack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pack.service.AuthorService;
import pack.entities.Author;

//If working with profiles:
//@ActiveProfiles(profiles = "dev")

@SpringBootTest
class AuthorTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void create() {
        //remember! cannot add the combination of
        // firstName+lastName twice;
        Author author1 = new Author("Enosh","Tsur");
        try {
            Author afterSave = authorService.save(author1);
            System.out.println(afterSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void findAll() {
        for (Author author : authorService.findAll()
             ) {
            System.out.println(author);
        }
    }
    @Test
    void findById() {
        System.out.println(authorService.findById(1L));
    }
    @Test
    void deleteById() {
        try {
            System.out.println(authorService.deleteById(2L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void update() {
        try {
            System.out.println(authorService.save(new Author(
                    1L,"Eran", "Asaraf")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
