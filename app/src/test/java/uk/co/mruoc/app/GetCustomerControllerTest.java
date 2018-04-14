package uk.co.mruoc.app;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.StubbedCustomerDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class GetCustomerControllerTest {

    private final GetCustomerController controller = new GetCustomerController();
    private final CustomerDto stubbedCustomer = new StubbedCustomerDto();

    @Test
    public void shouldReturnStubbedCustomer() {
        CustomerDto customer = controller.getCustomer(stubbedCustomer.getAccountNumber());

        assertThat(customer).isEqualToComparingFieldByFieldRecursively(stubbedCustomer);
    }

    @Test
    public void shouldThrowCustomerNotFoundException() {
        String accountNumber = "9999999999";

        Throwable thrown = catchThrowable(() -> controller.getCustomer(accountNumber));

        assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class)
                .hasNoCause()
                .hasMessage(accountNumber);
    }

}
