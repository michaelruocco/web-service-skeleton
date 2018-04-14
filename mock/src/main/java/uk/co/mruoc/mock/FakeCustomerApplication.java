package uk.co.mruoc.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.StubbedCustomerDto;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class FakeCustomerApplication implements AutoCloseable {

    private final CustomerDtoConverter customerConverter = new CustomerDtoConverter();

    private final WireMockServer server;

    public FakeCustomerApplication() {
        this(new WireMockConfiguration().dynamicPort());
    }

    public FakeCustomerApplication(WireMockConfiguration configuration) {
        server = new WireMockServer(configuration);
        stubFor(new StubbedCustomerDto());
    }

    public int getPort() {
        return server.port();
    }

    private void stubFor(CustomerDto customer) {
        String url = String.format("/customers/%s", customer.getAccountNumber());
        server.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(customerConverter.toJson(customer))));
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
