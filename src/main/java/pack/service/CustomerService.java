package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pack.entities.Customer;
import pack.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Component // To insert tha @Autowired constructor to spring context;
public class CustomerService {

    private CustomerRepository repository;
    private BookService bookService;

    @Autowired
    public CustomerService(CustomerRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public Customer save (Customer customer) throws Exception {
        try {
            return repository.save(customer);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Customer> findById (Long id) {
        return repository.findById(id);
    }

    public List<Customer> findAll () {
        return (List<Customer>) repository.findAll();
    }

    public Customer deleteById(Long id) throws Exception {
        return findById(id).map(it -> {
            repository.delete(it);
            return it;
        }).orElseThrow(() ->
            new Exception("Customer With ID "+id+" Is Not Exist")
        );
    }

    public void addPurchase(Long customerId, Long bookId) throws Exception{
        try{
            findById(customerId).ifPresent(customer ->
                    bookService.findById(bookId).ifPresent(book -> {
            if (!customer.getBooks().contains(book)) {
                customer.getBooks().add(book);
                repository.save(customer);
            }
                    }));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deletePurchase(Long customerId, Long bookId) throws Exception{
        try {
            repository.deleteCustomerBook(customerId, bookId);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
