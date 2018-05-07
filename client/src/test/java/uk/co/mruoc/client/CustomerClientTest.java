package uk.co.mruoc.client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCreateCustomerDto;
import uk.co.mruoc.api.examples.StubbedCreateFailureCustomerDto;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;
import uk.co.mruoc.mock.FakeCustomerApplicationRule;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CustomerClientTest {

    @Rule
    public final FakeCustomerApplicationRule applicationRule = new FakeCustomerApplicationRule();

    private CustomerClient client;

    @Before
    public void setUp() {
        client = new CustomerClient("http://localhost:" + applicationRule.getPort());
    }

    @Test
    public void shouldReturnCustomer() {
        String accountNumber = "1111111111";

        Optional<CustomerDto> customer = client.getCustomer(accountNumber);

        assertThat(customer.isPresent()).isTrue();
        assertThat(customer.get()).isEqualToComparingFieldByFieldRecursively(new StubbedCustomerDto1());
    }

    @Test
    public void shouldNotReturnCustomerIfNotFound() {
        String accountNumber = "9999999999";

        Optional<CustomerDto> customer = client.getCustomer(accountNumber);

        assertThat(customer.isPresent()).isFalse();
    }

    @Test
    public void shouldNotReturnCustomerIfInvalidFormatAccountNumber() {
        String accountNumber = "99999999999";

        Optional<CustomerDto> customer = client.getCustomer(accountNumber);

        assertThat(customer.isPresent()).isFalse();
    }

    @Test
    public void shouldCreateCustomer() {
        CustomerDto customer = new StubbedCreateCustomerDto();

        CustomerDto createCustomer = client.createCustomer(customer);

        assertThat(createCustomer).isEqualToComparingFieldByFieldRecursively(customer);
    }

    @Test
    public void shouldThrowExceptionIfCreateCustomerHasBadRequest() {
        CustomerDto customer = new StubbedCreateFailureCustomerDto();

        Throwable thrown = catchThrowable(() -> client.createCustomer(customer));

        assertThat(thrown).isInstanceOf(CustomerClientException.class)
                .hasNoCause()
                .hasMessage("invalid account number, should be 10 digit numeric: 222abc");
    }

    @Test
    public void shouldThrowExceptionIfCreateCustomerAlreadyExists() {
        CustomerDto customer = new StubbedCustomerDto1();

        Throwable thrown = catchThrowable(() -> client.createCustomer(customer));

        assertThat(thrown).isInstanceOf(CustomerClientException.class)
                .hasNoCause()
                .hasMessage("customer with account number 1111111111 already exists");
    }

}
