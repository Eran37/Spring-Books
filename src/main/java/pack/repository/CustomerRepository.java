package pack.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pack.entities.Customer;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Modifying
    @Query(
            value = "delete from customer_books where customers_id = :customerId and books_id = :bookId",
            nativeQuery = true
    )
    void deleteCustomerBook(@Param("customerId") Long customerId, @Param("bookId") Long bookId);
}
