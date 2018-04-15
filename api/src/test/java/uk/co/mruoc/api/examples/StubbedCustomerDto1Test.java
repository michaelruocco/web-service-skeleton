package uk.co.mruoc.api.examples;

import org.junit.Test;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedAddressDto1;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedCustomerDto1Test {

    private final CustomerDto customer = new StubbedCustomerDto1();

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(customer.getAccountNumber()).isEqualTo("1111111111");
    }

    @Test
    public void shouldReturnFirstName() {
        assertThat(customer.getFirstName()).isEqualTo("Joe");
    }

    @Test
    public void shouldReturnLastName() {
        assertThat(customer.getLastName()).isEqualTo("Bloggs");
    }

    @Test
    public void shouldReturnAddresses() {
        AddressDto expectedAddress = new StubbedAddressDto1();

        List<AddressDto> addresses = customer.getAddresses();

        assertThat(addresses).usingFieldByFieldElementComparator().contains(expectedAddress);
    }

}
