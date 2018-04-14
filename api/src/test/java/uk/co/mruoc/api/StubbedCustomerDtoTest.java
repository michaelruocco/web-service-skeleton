package uk.co.mruoc.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedCustomerDtoTest {

    private final StubbedCustomerDto customer = new StubbedCustomerDto();

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
        assertThat(customer.getAddresses()).usingFieldByFieldElementComparator().contains(new StubbedAddressDto());
    }

}
