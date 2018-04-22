package uk.co.mruoc.client;

import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.ErrorDto;
import uk.co.mruoc.api.ErrorDtoConverter;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.util.Optional;

public class CustomerClient {

    private final CustomerDtoConverter customerConverter = new CustomerDtoConverter();
    private final ErrorDtoConverter errorConverter = new ErrorDtoConverter();

    private final HttpClient httpClient;
    private final String baseUrl;

    public CustomerClient(String baseUrl) {
        this(baseUrl, new SimpleHttpClient());
    }

    public CustomerClient(String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    public Optional<CustomerDto> getCustomer(String accountNumber) {
        String url = buildGetCustomerEndpoint(accountNumber);
        Response response = httpClient.get(url);
        if (response.is2xx()) {
            return Optional.of(customerConverter.toDto(response.getBody()));
        }
        return Optional.empty();
    }

    public CustomerDto createCustomer(CustomerDto customer) {
        String url = buildCustomersEndpoint();
        String body = customerConverter.toJson(customer);
        Response response = httpClient.post(url, body);
        if (response.is2xx()) {
            return customerConverter.toDto(response.getBody());
        }
        ErrorDto error = errorConverter.toDto(response.getBody());
        throw new CustomerClientException(error.getMessage());
    }

    private String buildGetCustomerEndpoint(String accountNumber) {
        return buildCustomersEndpoint() + "/" + accountNumber;
    }

    private String buildCustomersEndpoint() {
        return String.format("%s/customers", baseUrl);
    }

}
