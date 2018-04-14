package uk.co.mruoc.app;

import uk.co.mruoc.api.CustomerDto;

import java.util.Optional;

public interface CustomerFacade {

    Optional<CustomerDto> getCustomer(String accountNumber);

}
