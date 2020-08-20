package pack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pack.entities.Book;
import pack.service.AuthorService;
import pack.service.BookService;

import java.time.LocalDate;

@SpringBootTest
class BookTest {

    @Autowired
    private BookService service;

    @Test
    void create() {
        Book book = new Book(
                "Friends&Eran", LocalDate.of(
                        2020,8,20)
                            ,37.00);
        try {
            Book afterSave = service.create(book,1L);
            System.out.println(afterSave);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void update() {
        Book book = new Book(
                "Friendim&Eranim", LocalDate.now().minusYears(4),137.90);
        try {
            Book afterSave = service.create(book,1L);
            System.out.println(afterSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateExample2() {
        service.findById(1L).ifPresent(it -> {
            it.setPrice(200.00);
            it.setTitle("TheNewTitle");
            try {
                service.update(it);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }



}
