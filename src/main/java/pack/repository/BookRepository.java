package pack.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pack.entities.Book;

import javax.transaction.Transactional;

//Adding @Transactional annotation to make this Interface a transactional interface
//So we can wright Transactional methods comfortably;
@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
//-------------------------------------------------------------------------------------------------
    //Transactional method (Acceptable in repository interface):

    //Written in JPQL; the description in JPQL refers to objects(more likely with java language) -
    // not to raws and columns

    @Modifying // @Modifying means that the method is going to change the DB
                    // (not only to get data - delete method)

    //(The Query - JPQL language:
        // Java Persistence API Query Language)
    @Query("delete from Book b where b.id = :id")
    void deleteById(@Param("id") Long id);
//-------------------------------------------------------------------------------------------------
}
