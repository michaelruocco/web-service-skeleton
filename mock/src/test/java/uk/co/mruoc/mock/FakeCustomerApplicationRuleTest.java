package uk.co.mruoc.mock;

import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.api.AbstractAccountNumberErrorDto;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.ErrorDto;
import uk.co.mruoc.api.ErrorDtoConverter;
import uk.co.mruoc.api.InvalidAccountNumberFormatErrorDto;
import uk.co.mruoc.api.examples.StubbedCreateCustomerDto;
import uk.co.mruoc.api.examples.StubbedCreateFailureCustomerDto;
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
        CustomerDto customer = new StubbedCustomerDto1();
        String accountNumber = customer.getAccountNumber();
        String url = buildGetCustomerUrl(accountNumber);

        Response response = client.get(url);

        CustomerDtoConverter converter = new CustomerDtoConverter();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(converter.toJson(customer));
    }

    @Test
    public void shouldReturnNotFound() {
        getTestAccountNumberErrorDto(new StubbedCustomerNotFoundErrorDto1());
    }

    @Test
    public void shouldReturnInvalidAccountNumberIfNonNumeric() {
        getTestAccountNumberErrorDto(new StubbedNonNumericAccountNumberErrorDto());
    }

    @Test
    public void shouldReturnInvalidAccountNumberIfShorterThanTenDigits() {
        getTestAccountNumberErrorDto(new StubbedShortAccountNumberErrorDto());
    }

    @Test
    public void shouldReturnInvalidAccountNumberIfLongerThanTenDigits() {
        getTestAccountNumberErrorDto(new StubbedLongAccountNumberErrorDto());
    }

    @Test
    public void shouldReturnCreatedCustomer() {
        CustomerDtoConverter converter = new CustomerDtoConverter();
        CustomerDto customer = new StubbedCreateCustomerDto();
        String url = buildCustomersUrl();

        Response response = client.post(url, converter.toJson(customer));

        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(converter.toJson(customer));
    }

    @Test
    public void shouldReturnErrorIfCreatedCustomerIsNotValid() {
        CustomerDtoConverter customerConverter = new CustomerDtoConverter();
        CustomerDto customer = new StubbedCreateFailureCustomerDto();
        String url = buildCustomersUrl();

        Response response = client.post(url, customerConverter.toJson(customer));

        ErrorDtoConverter errorConverter = new ErrorDtoConverter();
        ErrorDto expectedError = new InvalidAccountNumberFormatErrorDto(customer.getAccountNumber());
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(errorConverter.toJson(expectedError));
    }


    private void getTestAccountNumberErrorDto(AbstractAccountNumberErrorDto errorDto) {
        String url = buildGetCustomerUrl(errorDto.getAccountNumber());

        Response response = client.get(url);

        ErrorDtoConverter converter = new ErrorDtoConverter();
        assertThat(response.getStatusCode()).isEqualTo(errorDto.getStatusCode());
        assertThat(response.getBody()).isEqualToIgnoringWhitespace(converter.toJson(errorDto));
    }

    private String buildGetCustomerUrl(String accountNumber) {
        String url = buildCustomersUrl();
        return url + "/" + accountNumber;
    }

    private String buildCustomersUrl() {
        int port = applicationRule.getPort();
        return String.format("http://localhost:%d/customers", port);
    }

}
