package uk.co.mruoc.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.CustomerNotFoundErrorDto;
import uk.co.mruoc.api.ErrorDtoConverter;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;
import uk.co.mruoc.api.examples.StubbedCustomerNotFoundErrorDto1;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class FakeCustomerApplication implements AutoCloseable {

    private final CustomerDtoConverter customerConverter = new CustomerDtoConverter();
    private final ErrorDtoConverter errorConverter = new ErrorDtoConverter();

    private final WireMockServer server;

    public FakeCustomerApplication() {
        this(new WireMockConfiguration().dynamicPort());
    }

    public FakeCustomerApplication(WireMockConfiguration configuration) {
        server = new WireMockServer(configuration);
        stubFor(new StubbedCustomerDto1());
        stubError(new StubbedCustomerNotFoundErrorDto1());
    }

    public int getPort() {
        return server.port();
    }

    private void stubFor(CustomerDto customer) {
        String url = buildUrl(customer.getAccountNumber());
        server.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(customerConverter.toJson(customer))));
    }

    private void stubError(CustomerNotFoundErrorDto error) {
        String url = buildUrl(error.getAccountNumber());
        server.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(errorConverter.toJson(error))));
    }

    private String buildUrl(String accountNumber) {
        return String.format("/customers/%s", accountNumber);
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

    @Override
    public void close() {
        stop();
    }

}
