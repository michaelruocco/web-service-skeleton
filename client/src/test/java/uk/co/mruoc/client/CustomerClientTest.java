package uk.co.mruoc.client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.StubbedCustomerDto;
import uk.co.mruoc.mock.FakeCustomerApplicationRule;

import static org.assertj.core.api.Assertions.assertThat;

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

        CustomerDto customer = client.getCustomer(accountNumber);

        assertThat(customer).isEqualToComparingFieldByFieldRecursively(new StubbedCustomerDto());
    }

}
