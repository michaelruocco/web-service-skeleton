package uk.co.mruoc.api.examples;

import org.junit.Test;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedCreateCustomerDtoTest {

    private final CustomerDto customer = new StubbedCreateCustomerDto();

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(customer.getAccountNumber()).isEqualTo("2222222222");
    }

    @Test
    public void shouldReturnFirstName() {
        assertThat(customer.getFirstName()).isEqualTo("Homer");
    }

    @Test
    public void shouldReturnLastName() {
        assertThat(customer.getLastName()).isEqualTo("Simpson");
    }

    @Test
    public void shouldReturnAddresses() {
        AddressDto expectedAddress = new StubbedAddressDto2();

        List<AddressDto> addresses = customer.getAddresses();

        assertThat(addresses).usingFieldByFieldElementComparator().contains(expectedAddress);
    }

}
