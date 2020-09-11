package pack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.entities.Customer;
import pack.service.CustomerService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) { this.service = service; }

    @GetMapping("/byId/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Customer> customer = service.findById(id);
        return customer.isPresent() ?
                ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(customer.get())
                :
                ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("The Customer With The ID "+id+" Have Not Found");
    }

    @PostMapping("/create")
    public ResponseEntity create (@RequestBody Customer customer) {
        try {
            Customer afterSave = service.save(customer);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(afterSave);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Customer customer) {
        if (customer.getId() == null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("In Order To Update, Customer Must Have An ID");
        }
        return create(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            Customer deleted = service.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(deleted);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        ArrayList<Customer> all;
        all = (ArrayList<Customer>) service.findAll();
        return all.isEmpty() ?
                ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("No Customers On DB")
                :
                ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(all);

    }

}
