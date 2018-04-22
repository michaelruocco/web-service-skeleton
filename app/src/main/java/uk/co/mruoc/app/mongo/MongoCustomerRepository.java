package uk.co.mruoc.app.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import uk.co.mruoc.app.model.Customer;
import uk.co.mruoc.app.model.CustomerRepository;

import java.util.Optional;

@Component
public interface MongoCustomerRepository extends CustomerRepository, MongoRepository<Customer, String> {

    default Optional<Customer> findByAccountNumber(String accountNumber) {
        return findById(accountNumber);
    }

    default Customer create(Customer customer) {
        return insert(customer);
    }

}
