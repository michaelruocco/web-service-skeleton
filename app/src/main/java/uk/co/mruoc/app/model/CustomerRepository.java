package uk.co.mruoc.app.model;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findByAccountNumber(String accountNumber);

    Customer create(Customer customer);

}
