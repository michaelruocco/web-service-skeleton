package uk.co.mruoc.app.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import uk.co.mruoc.app.model.Customer;
import uk.co.mruoc.app.model.CustomerRepository;

import java.util.Optional;

@Component
public interface MongoCustomerRepository extends CustomerRepository, MongoRepository<MongoCustomer, String> {

    default Optional<Customer> findByAccountNumber(String accountNumber) {
        Optional<MongoCustomer> mongoCustomer = findById(accountNumber);
        if (mongoCustomer.isPresent()) {
            return Optional.of(mongoCustomer.get());
        }
        return Optional.empty();
    }

    default Customer create(Customer customer) {
        MongoCustomer mongoCustomer = (MongoCustomer) customer;
        return insert(mongoCustomer);
    }

    default boolean exists(String accountNumber) {
        return existsById(accountNumber);
    }

}
