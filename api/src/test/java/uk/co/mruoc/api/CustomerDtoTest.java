package uk.co.mruoc.api;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto.CustomerDtoBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDtoTest {

    private final CustomerDtoBuilder builder = new CustomerDtoBuilder();

    @Test
    public void shouldSetAccountNumber() {
        String accountNumber = "1111111111";
        builder.setAccountNumber(accountNumber);

        CustomerDto customer = builder.build();

        assertThat(customer.getAccountNumber()).isEqualTo(accountNumber);
    }

    @Test
    public void shouldSetFirstName() {
        String firstName = "Michael";
        builder.setFirstName(firstName);

        CustomerDto customer = builder.build();

        assertThat(customer.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void shouldSetLastName() {
        String lastName = "Ruocco";
        builder.setLastName(lastName);

        CustomerDto customer = builder.build();

        assertThat(customer.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void addressesShouldBeEmptyByDefault() {
        CustomerDto customer = builder.build();

        assertThat(customer.getAddresses()).isEmpty();
    }

    @Test
    public void shouldAddAddress() {
        AddressDto address = new AddressDto();
        builder.addAddress(address);

        CustomerDto customer = builder.build();

        assertThat(customer.getAddresses()).containsExactly(address);
    }

    @Test
    public void shouldSetAddresses() {
        List<AddressDto> addresses = new ArrayList<>();
        builder.setAddresses(addresses);

        CustomerDto customer = builder.build();

        assertThat(customer.getAddresses()).isEqualTo(addresses);
    }

    @Test
    public void shouldReturnSpecificAddress() {
        AddressDto address = new AddressDto();
        builder.addAddress(address);

        CustomerDto customer = builder.build();

        assertThat(customer.getAddress(0)).isEqualTo(address);
    }

    @Test
    public void shouldHaveNoArgumentConstructorForJackson() {
        assertThat(new CustomerDto()).isNotNull();
    }

}
