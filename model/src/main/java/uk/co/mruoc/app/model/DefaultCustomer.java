package uk.co.mruoc.app.model;

import java.util.List;


public class DefaultCustomer implements Customer {

    private String accountNumber;
    private String firstName;
    private String lastName;
    private List<Address> addresses;

    public DefaultCustomer() {
        // intentionally blank
    }

    public DefaultCustomer(String accountNumber,
                           String firstName,
                           String lastName,
                           List<Address> addresses) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public List<Address> getAddresses() {
        return addresses;
    }

}
