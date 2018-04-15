package uk.co.mruoc.app.facade;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultCustomerFacadeTest {

    private static final String NOT_FOUND_ACCOUNT_NUMBER = "9999999999";

    private final CustomerFacade facade = new DefaultCustomerFacade();
    private final CustomerDto stubbedCustomer = new StubbedCustomerDto1();

    @Test
    public void shouldReturnStubbedCustomer() {
        Optional<CustomerDto> customer = facade.getCustomer(stubbedCustomer.getAccountNumber());

        assertThat(customer.isPresent()).isTrue();
        assertThat(customer.get()).isEqualToComparingFieldByFieldRecursively(stubbedCustomer);
    }

    @Test
    public void shouldNotReturnCustomerIfNotFound() {
        Optional<CustomerDto> customer = facade.getCustomer(NOT_FOUND_ACCOUNT_NUMBER);

        assertThat(customer.isPresent()).isFalse();
    }

}
