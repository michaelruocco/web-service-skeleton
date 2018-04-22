package uk.co.mruoc.mock;

import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.api.AbstractAccountNumberErrorDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.ErrorDtoConverter;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;
import uk.co.mruoc.api.examples.StubbedCustomerNotFoundErrorDto1;
import uk.co.mruoc.api.examples.StubbedLongAccountNumberErrorDto;
import uk.co.mruoc.api.examples.StubbedNonNumericAccountNumberErrorDto;
import uk.co.mruoc.api.examples.StubbedShortAccountNumberErrorDto;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeCustomerApplicationRuleTest {

    @Rule
    public FakeCustomerApplicationRule applicationRule = new FakeCustomerApplicationRule();


    private final HttpClient client = new SimpleHttpClient();

    @Test
    public void shouldReturnStubbedCustomer() {
        StubbedCustomerDto1 customer = new StubbedCustomerDto1();
        String accountNumber = customer.getAccountNumber();
        String url = buildGetCustomerUrl(accountNumber);

        Response response = client.get(url);

        CustomerDtoConverter converter = new CustomerDtoConverter();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(converter.toJson(customer));
    }

    @Test
    public void shouldReturnNotFound() {
        testAccountNumberErrorDto(new StubbedCustomerNotFoundErrorDto1());
    }

    @Test
    public void shouldReturnInvalidAccountNumberIfNonNumeric() {
        testAccountNumberErrorDto(new StubbedNonNumericAccountNumberErrorDto());
    }

    @Test
    public void shouldReturnInvalidAccountNumberIfShorterThanTenDigits() {
        testAccountNumberErrorDto(new StubbedShortAccountNumberErrorDto());
    }

    @Test
    public void shouldReturnInvalidAccountNumberIfLongerThanTenDigits() {
        testAccountNumberErrorDto(new StubbedLongAccountNumberErrorDto());
    }

    private void testAccountNumberErrorDto(AbstractAccountNumberErrorDto errorDto) {
        String url = buildGetCustomerUrl(errorDto.getAccountNumber());

        Response response = client.get(url);

        ErrorDtoConverter converter = new ErrorDtoConverter();
        assertThat(response.getStatusCode()).isEqualTo(errorDto.getStatusCode());
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(converter.toJson(errorDto));
    }

    private String buildGetCustomerUrl(String accountNumber) {
        int port = applicationRule.getPort();
        return String.format("http://localhost:%d/customers/%s", port, accountNumber);
    }

}
