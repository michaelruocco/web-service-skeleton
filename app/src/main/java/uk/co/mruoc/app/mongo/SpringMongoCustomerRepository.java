package uk.co.mruoc.app.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.mruoc.app.model.Customer;

public interface SpringMongoCustomerRepository extends MongoRepository<Customer, String> {

    // intentionally blank

}
