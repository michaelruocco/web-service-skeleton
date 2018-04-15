package uk.co.mruoc.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.mruoc.app.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByAccountNumber(String accountNumber);

}
