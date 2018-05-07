package uk.co.mruoc.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import uk.co.mruoc.api.AbstractAccountNumberErrorDto;
import uk.co.mruoc.api.CustomerAlreadyExistsErrorDto;
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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
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

        CustomerDto stubbedCustomer1 = new StubbedCustomerDto1();
        stubGetFor(stubbedCustomer1);

        stubGetError(new StubbedCustomerNotFoundErrorDto1());
        stubGetError(new StubbedNonNumericAccountNumberErrorDto());
        stubGetError(new StubbedShortAccountNumberErrorDto());
        stubGetError(new StubbedLongAccountNumberErrorDto());

        stubPostFor(new StubbedCreateCustomerDto());

        CustomerDto createFailureDto = new StubbedCreateFailureCustomerDto();
        stubPostError(createFailureDto, new InvalidAccountNumberFormatErrorDto(createFailureDto.getAccountNumber()));

        stubPostError(stubbedCustomer1, new CustomerAlreadyExistsErrorDto(stubbedCustomer1.getAccountNumber()));
    }

    public int getPort() {
        return server.port();
    }

    private void stubGetFor(CustomerDto customer) {
        String url = buildUrl(customer.getAccountNumber());
        server.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(customerConverter.toJson(customer))));
    }

    private void stubGetError(AbstractAccountNumberErrorDto error) {
        String url = buildUrl(error.getAccountNumber());
        server.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(error.getStatusCode())
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(errorConverter.toJson(error))));
    }

    private void stubPostFor(CustomerDto customer) {
        String url = buildUrl();
        String body = customerConverter.toJson(customer);
        server.stubFor(post(urlEqualTo(url))
                .withRequestBody(equalToJson(body))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(body)));
    }

    private void stubPostError(CustomerDto customer, ErrorDto error) {
        String url = buildUrl();
        String body = customerConverter.toJson(customer);
        server.stubFor(post(urlEqualTo(url))
                .withRequestBody(equalToJson(body))
                .willReturn(aResponse()
                        .withStatus(error.getStatusCode())
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(errorConverter.toJson(error))));
    }

    private String buildUrl() {
        return "/customers";
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
