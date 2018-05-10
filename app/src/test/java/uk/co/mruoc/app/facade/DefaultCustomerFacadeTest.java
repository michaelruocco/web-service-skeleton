package uk.co.mruoc.app.facade;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;
import uk.co.mruoc.app.model.Customer;
import uk.co.mruoc.app.model.CustomerAlreadyExistsException;
import uk.co.mruoc.app.CustomerAssert;
import uk.co.mruoc.app.model.FakeCustomerRepository;
import uk.co.mruoc.app.model.StubbedCustomer;
import uk.co.mruoc.app.mongo.MongoCustomerConverter;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DefaultCustomerFacadeTest {

    private static final String NOT_FOUND_ACCOUNT_NUMBER = "9999999999";

    private final FakeCustomerRepository repository = new FakeCustomerRepository();
    private final MongoCustomerConverter modelConverter = new MongoCustomerConverter();
    private final CustomerFacade facade = new DefaultCustomerFacade(repository, modelConverter);
    private final CustomerDto customer = new StubbedCustomerDto1();

    @Test
    public void shouldReturnStubbedCustomer() {
        repository.setCustomerToFind(new StubbedCustomer());

        Optional<CustomerDto> returnedCustomer = facade.getCustomer(customer.getAccountNumber());

        assertThat(returnedCustomer.isPresent()).isTrue();
        assertThat(returnedCustomer.get()).isEqualToComparingFieldByFieldRecursively(customer);
    }

    @Test
    public void shouldNotReturnCustomerIfNotFound() {
        Optional<CustomerDto> customer = facade.getCustomer(NOT_FOUND_ACCOUNT_NUMBER);

        assertThat(customer.isPresent()).isFalse();
    }

    @Test
    public void shouldThrowExceptionIfCustomerAlreadyExists() {
        repository.setCustomerToFind(new StubbedCustomer());

        Throwable thrown = catchThrowable(() -> facade.createCustomer(customer));

        assertThat(thrown)
                .isInstanceOf(CustomerAlreadyExistsException.class)
                .hasNoCause()
                .hasMessage(customer.getAccountNumber());
    }

    @Test
    public void shouldCreateCustomerWithSameValuesAsDto() {
        facade.createCustomer(customer);

        Customer createdCustomer = repository.getLastCreatedCustomer();
        CustomerAssert.assertThat(createdCustomer).isEqualTo(customer);
    }

}
