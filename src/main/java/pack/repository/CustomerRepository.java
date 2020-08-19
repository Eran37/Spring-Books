package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
