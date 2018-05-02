package uk.co.mruoc.app.facade;

import uk.co.mruoc.api.CustomerDto;

import java.util.Optional;

public interface CustomerFacade {

    Optional<CustomerDto> getCustomer(String accountNumber);

    CustomerDto createCustomer(CustomerDto customerDto);
    
}
