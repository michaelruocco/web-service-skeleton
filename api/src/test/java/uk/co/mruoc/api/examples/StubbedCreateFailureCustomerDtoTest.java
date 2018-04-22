package uk.co.mruoc.api.examples;

import org.junit.Test;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedCreateFailureCustomerDtoTest {

    private final CustomerDto customer = new StubbedCreateFailureCustomerDto();

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(customer.getAccountNumber()).isEqualTo("222abc");
    }

    @Test
    public void shouldReturnFirstName() {
        assertThat(customer.getFirstName()).isEqualTo("Invalid");
    }

    @Test
    public void shouldReturnLastName() {
        assertThat(customer.getLastName()).isEqualTo("AccountNumber");
    }

    @Test
    public void shouldReturnAddresses() {
        AddressDto expectedAddress = new StubbedAddressDto2();

        List<AddressDto> addresses = customer.getAddresses();

        assertThat(addresses).usingFieldByFieldElementComparator().contains(expectedAddress);
    }

}
