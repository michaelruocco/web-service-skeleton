package uk.co.mruoc.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.io.IOException;

public class CustomerClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient;
    private final String baseUrl;

    public CustomerClient(String baseUrl) {
        this(baseUrl, new SimpleHttpClient());
    }

    public CustomerClient(String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    public CustomerDto getCustomer(String accountNumber) {
        String url = String.format("%s/customers/%s", baseUrl, accountNumber);
        Response response = httpClient.get(url);
        return toCustomer(response.getBody());
    }

    private CustomerDto toCustomer(String json) {
        try {
            return objectMapper.readValue(json, CustomerDto.class);
        } catch (IOException e) {
            throw new CustomerClientException(e);
        }
    }

}
