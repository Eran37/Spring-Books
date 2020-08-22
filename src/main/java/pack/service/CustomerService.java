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

    public List<Customer> findAll () {
        return (List<Customer>) repository.findAll();
    }

    public Optional<Customer> findById (Long id) {
        return repository.findById(id);
    }

    public Customer save (Customer customer) throws Exception {
        try {
            return repository.save(customer);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Customer deleteById(Long id) throws Exception {
        return findById(id).map(it -> {
            repository.delete(it);
            return it;
        }).orElseThrow(() ->
            new Exception("Customer With ID "+id+" Is Not Exist")
        );
    }

}
