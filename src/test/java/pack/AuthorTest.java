package pack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pack.dao.AuthorDao;
import pack.entities.Author;

//If working with profiles:
//@ActiveProfiles(profiles = "dev")

@SpringBootTest
class AuthorTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void create() {
        //remember! cannot add the combination of
        // firstName+lastName twice;
        Author author1 = new Author("Enosh","Tsur");
        try {
            Author afterSave = authorDao.save(author1);
            System.out.println(afterSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void findAll() {
        for (Author author : authorDao.findAll()
             ) {
            System.out.println(author);
        }
    }

    @Test
    void deleteById() {
        try {
            System.out.println(authorDao.deleteById(2L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
