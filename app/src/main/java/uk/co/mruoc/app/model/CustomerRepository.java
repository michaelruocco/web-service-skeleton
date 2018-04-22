package uk.co.mruoc.app.model;

public interface CustomerRepository {

    Customer findByAccountNumber(String accountNumber);

    Customer create(Customer customer);

}
