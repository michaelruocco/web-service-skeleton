package uk.co.mruoc.app.controllers;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;
import uk.co.mruoc.app.facade.CustomerFacade;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetCustomerControllerTest {

    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final CustomerDto customer = new StubbedCustomerDto1();

    private final GetCustomerController controller = new GetCustomerController(facade);

    @Test
    public void shouldReturnStubbedCustomer() {
        given(facade.getCustomer(customer.getAccountNumber())).willReturn(Optional.of(customer));

        CustomerDto customer = controller.getCustomer(this.customer.getAccountNumber());

        assertThat(customer).isEqualTo(this.customer);
    }

    @Test
    public void shouldThrowCustomerNotFoundException() {
        given(facade.getCustomer(customer.getAccountNumber())).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> controller.getCustomer(customer.getAccountNumber()));

        assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class)
                .hasNoCause()
                .hasMessage(customer.getAccountNumber());
    }

}
