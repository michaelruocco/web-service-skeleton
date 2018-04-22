package uk.co.mruoc.app.mongo;

import org.springframework.data.annotation.Id;
import uk.co.mruoc.app.model.Address;
import uk.co.mruoc.app.model.Customer;

import java.util.List;

public class MongoCustomer implements Customer {

    @Id
    private String accountNumber;
    private String firstName;
    private String lastName;
    private List<Address> addresses;

    public MongoCustomer() {
        // intentionally blank
    }

    public MongoCustomer(String accountNumber,
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
