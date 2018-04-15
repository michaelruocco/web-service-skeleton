package uk.co.mruoc.app.model;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerConverterTest {

    private final CustomerConverter converter = new CustomerConverter();

    @Test
    public void shouldConvertDto() {
        CustomerDto customerDto = new StubbedCustomerDto1();

        Customer customerModel = converter.toModel(customerDto);

        assertSameValues(customerDto, customerModel);
    }

    @Test
    public void shouldConvertModel() {
        Customer customerModel = new Customer(new StubbedCustomerDto1());

        CustomerDto customerDto = converter.toDto(customerModel);

        assertSameValues(customerDto, customerModel);
    }

    private static void assertSameValues(CustomerDto dto, Customer model) {
        assertThat(dto.getAccountNumber()).isEqualTo(model.getAccountNumber());
        assertThat(dto.getFirstName()).isEqualTo(model.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(model.getLastName());
        assertThat(dto.getAddresses().size()).isEqualTo(model.getAddresses().size());
    }

}
