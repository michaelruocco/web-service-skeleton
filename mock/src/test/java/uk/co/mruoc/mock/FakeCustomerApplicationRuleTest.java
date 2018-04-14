package uk.co.mruoc.mock;

import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.StubbedCustomerDto;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeCustomerApplicationRuleTest {

    @Rule
    public FakeCustomerApplicationRule applicationRule = new FakeCustomerApplicationRule();

    private final StubbedCustomerDto customer = new StubbedCustomerDto();
    private final CustomerDtoConverter customerConverter = new CustomerDtoConverter();
    private final HttpClient client = new SimpleHttpClient();

    @Test
    public void shouldReturnStubbedCustomer() {
        int port = applicationRule.getPort();
        String accountNumber = customer.getAccountNumber();
        String url = String.format("http://localhost:%d/customers/%s", port, accountNumber);

        Response response = client.get(url);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(customerConverter.toJson(customer));
    }

}
