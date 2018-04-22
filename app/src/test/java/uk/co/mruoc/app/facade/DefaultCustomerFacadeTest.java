package uk.co.mruoc.app.facade;

import org.junit.Test;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.examples.StubbedCustomerDto1;
import uk.co.mruoc.app.model.CustomerRepository;
import uk.co.mruoc.app.mongo.MongoCustomerConverter;
import uk.co.mruoc.app.mongo.StubbedMongoCustomer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultCustomerFacadeTest {

    private static final String NOT_FOUND_ACCOUNT_NUMBER = "9999999999";

    private final CustomerRepository repository = mock(CustomerRepository.class);
    private final MongoCustomerConverter modelConverter = new MongoCustomerConverter();
    private final CustomerFacade facade = new DefaultCustomerFacade(repository, modelConverter);
    private final CustomerDto stubbedCustomer = new StubbedCustomerDto1();

    @Test
    public void shouldReturnStubbedCustomer() {
        given(repository.findByAccountNumber(stubbedCustomer.getAccountNumber())).willReturn(Optional.of(new StubbedMongoCustomer()));

        Optional<CustomerDto> customer = facade.getCustomer(stubbedCustomer.getAccountNumber());

        assertThat(customer.isPresent()).isTrue();
        assertThat(customer.get()).isEqualToComparingFieldByFieldRecursively(stubbedCustomer);
    }

    @Test
    public void shouldNotReturnCustomerIfNotFound() {
        given(repository.findByAccountNumber(NOT_FOUND_ACCOUNT_NUMBER)).willReturn(Optional.empty());

        Optional<CustomerDto> customer = facade.getCustomer(NOT_FOUND_ACCOUNT_NUMBER);

        assertThat(customer.isPresent()).isFalse();
    }

}
