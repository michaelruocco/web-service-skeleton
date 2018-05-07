package uk.co.mruoc.app.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCreateCustomerDto;
import uk.co.mruoc.app.facade.CustomerFacade;
import uk.co.mruoc.app.model.CustomerAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PostCustomersControllerTest {

    private static final String BASE_URL = "http://localhost:8080";
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final CustomerDto customer = new StubbedCreateCustomerDto();

    private final PostCustomersController controller = new PostCustomersController(facade);

    @Before
    public void setUp() {
        given(request.getRequestURL()).willReturn(new StringBuffer(BASE_URL));
    }

    @Test
    public void shouldReturnCreatedStatusCode() {
        given(facade.createCustomer(customer)).willReturn(customer);

        ResponseEntity<CustomerDto> response = controller.createCustomer(customer, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldCreateCustomer() {
        given(facade.createCustomer(customer)).willReturn(customer);

        ResponseEntity<CustomerDto> response = controller.createCustomer(customer, request);

        CustomerDto createdCustomer = response.getBody();
        assertThat(createdCustomer).isEqualTo(customer);
    }

    @Test
    public void shouldReturnLocationHeaderThatReferencesCreatedCustomer() {
        given(facade.createCustomer(customer)).willReturn(customer);

        ResponseEntity<CustomerDto> response = controller.createCustomer(customer, request);

        URI location = response.getHeaders().getLocation();
        assertThat(location.toString()).isEqualTo(BASE_URL + "/" + customer.getAccountNumber());
    }

    @Test
    public void shouldThrowCustomerAlreadyExistsException() {
        Exception exception = new CustomerAlreadyExistsException(customer.getAccountNumber());
        given(facade.createCustomer(customer)).willThrow(exception);

        Throwable thrown = catchThrowable(() -> controller.createCustomer(customer, request));

        assertThat(thrown).isEqualTo(exception);
    }

}
