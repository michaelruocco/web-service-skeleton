package uk.co.mruoc.app.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCustomerRepository implements CustomerRepository {

    private Map<String, Customer> customersToFind = new HashMap<>();
    private Customer lastCreatedCustomer;

    @Override
    public Optional<Customer> findByAccountNumber(String accountNumber) {
        if (customersToFind.containsKey(accountNumber)) {
            return Optional.of(customersToFind.get(accountNumber));
        }
        return Optional.empty();
    }

    @Override
    public Customer create(Customer customer) {
        return lastCreatedCustomer = customer;
    }

    @Override
    public boolean exists(String accountNumber) {
        return customersToFind.containsKey(accountNumber);
    }

    public void setCustomerToFind(Customer customer) {
        customersToFind.put(customer.getAccountNumber(), customer);
    }

    public Customer getLastCreatedCustomer() {
        return lastCreatedCustomer;
    }

}
