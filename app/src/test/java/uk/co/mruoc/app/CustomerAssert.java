package uk.co.mruoc.app;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.app.model.Address;
import uk.co.mruoc.app.model.Customer;

import java.util.List;
import java.util.Objects;

public class CustomerAssert extends AbstractAssert<CustomerAssert, Customer> {

    public CustomerAssert(Customer actual) {
        super(actual, CustomerAssert.class);
    }

    public CustomerAssert isEqualTo(CustomerDto dto) {
        return hasAccountNumber(dto.getAccountNumber())
                .hasFirstName(dto.getFirstName())
                .hasLastName(dto.getLastName())
                .hasAddresses(dto.getAddresses());
    }


    public static CustomerAssert assertThat(Customer actual) {
        return new CustomerAssert(actual);
    }

    public CustomerAssert hasAddresses(List<AddressDto> addresses) {
        isNotNull();
        List<Address> actualAddresses = actual.getAddresses();
        Assertions.assertThat(actualAddresses.size()).isEqualTo(addresses.size());
        for (int a = 0; a < addresses.size(); a++) {
            Address address = actualAddresses.get(a);
            AddressAssert.assertThat(address).isEqualTo(addresses.get(a));
        }
        return this;
    }

    public CustomerAssert hasAccountNumber(String accountNumber) {
        isNotNull();
        String actualAccountNumber = actual.getAccountNumber();
        if (!Objects.equals(actualAccountNumber, accountNumber)) {
            failWithMessage("Expected customers accountNumber to be <%s> but was <%s>", accountNumber, actualAccountNumber);
        }
        return this;
    }

    public CustomerAssert hasFirstName(String firstName) {
        isNotNull();
        String actualFirstName = actual.getFirstName();
        if (!Objects.equals(actualFirstName, firstName)) {
            failWithMessage("Expected customers firstName to be <%s> but was <%s>", firstName, actualFirstName);
        }
        return this;
    }

    public CustomerAssert hasLastName(String lastName) {
        isNotNull();
        String actualLastName = actual.getLastName();
        if (!Objects.equals(actualLastName, lastName)) {
            failWithMessage("Expected customers lastName to be <%s> but was <%s>", lastName, actualLastName);
        }
        return this;
    }

}
