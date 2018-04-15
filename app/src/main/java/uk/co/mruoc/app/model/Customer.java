package uk.co.mruoc.app.model;

import org.springframework.data.annotation.Id;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;

import java.util.List;
import java.util.stream.Collectors;

public class Customer {

    @Id
    private String accountNumber;
    private String firstName;
    private String lastName;
    private List<Address> addresses;

    public Customer() {
        // intentionally blank
    }

    public Customer(String accountNumber,
                    String firstName,
                    String lastName,
                    List<Address> addresses) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    public Customer(CustomerDto dto) {
        this(dto.getAccountNumber(),
                dto.getFirstName(),
                dto.getLastName(),
                toAddresses(dto.getAddresses()));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    private static List<Address> toAddresses(List<AddressDto> addressDtos) {
        return addressDtos.stream().map(Address::new).collect(Collectors.toList());
    }

}
