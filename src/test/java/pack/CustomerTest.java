package pack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pack.entities.Customer;
import pack.repository.CustomerRepository;
import pack.service.CustomerService;

import java.util.Optional;

@SpringBootTest
class CustomerTest {

    @Autowired
    CustomerService service;
    CustomerRepository repository;

    @Test
    void contextLoads() { }

    @Test
    void create() {
        Customer customer = new Customer(
                "Gali",
                "Segal",
                "galisegal@gmail.com");

        try {
            Customer afterSave = service.save(customer);
            System.out.println(afterSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findById() {
        service.findById(1L).ifPresent(System.out::println);
    }

    @Test
    void findAll() {
        service.findAll().forEach(System.out::println);
    }

    @Test
    void deleteById() {
        try {
            Customer deleted = service.deleteById(5L);
            System.out.println(deleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addPurchase() {
        try {
            service.addPurchase(2L,1L);
            System.out.println(service.findById(2L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void removePurchase() {
        try {
            service.deletePurchase(1L,1L);
            System.out.println(service.findById(1L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
