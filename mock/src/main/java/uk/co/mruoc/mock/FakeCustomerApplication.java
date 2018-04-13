package uk.co.mruoc.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import uk.co.mruoc.api.CustomerDto;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class FakeCustomerApplication implements AutoCloseable {

    private final ObjectMapper mapper = new ObjectMapper();
    private final WireMockServer server;

    public static void main(String[] args) {
        FakeCustomerApplication application = new FakeCustomerApplication(8080);
        application.start();
    }

    private FakeCustomerApplication(int port) {
        this(new WireMockConfiguration().port(port));
    }

    private FakeCustomerApplication(WireMockConfiguration configuration) {
        server = new WireMockServer(configuration);
        stubFor(new StubbedCustomer());
    }

    private void stubFor(CustomerDto customer) {
        String url = String.format("/customers/%s", customer.getAccountNumber());
        server.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(toJson(customer))));
    }

    private String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new FakeCustomerApplicationException(e);
        }
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
