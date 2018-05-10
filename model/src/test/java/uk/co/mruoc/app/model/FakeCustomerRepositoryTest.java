package uk.co.mruoc.app.model;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeCustomerRepositoryTest {

    private final Customer customer = new StubbedCustomer();
    private final FakeCustomerRepository repository = new FakeCustomerRepository();

    @Test
    public void lastCreatedCustomerShouldDefaultToNull() {
        assertThat(repository.getLastCreatedCustomer()).isNull();
    }

    @Test
    public void shouldReturnLastCreatedCustomer() {


        repository.create(customer);

        assertThat(repository.getLastCreatedCustomer()).isEqualTo(customer);
    }

    @Test
    public void shouldNotFindByAccountNumberIfNotConfigured() {
        Optional<Customer> result = repository.findByAccountNumber(customer.getAccountNumber());

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void shouldFindByAccountNumberIfConfigured() {
        repository.setCustomerToFind(customer);

        Optional<Customer> result = repository.findByAccountNumber(customer.getAccountNumber());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void customerShouldNotExistIfNotConfigured() {
        boolean exists = repository.exists(customer.getAccountNumber());

        assertThat(exists).isFalse();
    }

    @Test
    public void customerShouldExistIfNotConfigured() {
        repository.setCustomerToFind(customer);

        boolean exists = repository.exists(customer.getAccountNumber());

        assertThat(exists).isTrue();
    }

}
