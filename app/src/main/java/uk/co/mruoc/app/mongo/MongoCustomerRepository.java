package uk.co.mruoc.app.mongo;

import org.springframework.stereotype.Component;
import uk.co.mruoc.app.model.Customer;
import uk.co.mruoc.app.model.CustomerNotFoundException;
import uk.co.mruoc.app.model.CustomerRepository;

import java.util.Optional;

@Component
public class MongoCustomerRepository implements CustomerRepository {

    private final SpringMongoCustomerRepository springRepository;

    public MongoCustomerRepository(SpringMongoCustomerRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Customer findByAccountNumber(String accountNumber) {
        Optional<Customer> customer = springRepository.findById(accountNumber);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new CustomerNotFoundException(accountNumber);
    }

    @Override
    public Customer create(Customer customer) {
        return springRepository.insert(customer);
    }

}
