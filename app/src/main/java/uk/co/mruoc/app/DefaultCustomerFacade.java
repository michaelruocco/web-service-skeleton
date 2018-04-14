package uk.co.mruoc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.StubbedCustomerDto;

import java.util.Optional;

public class DefaultCustomerFacade implements CustomerFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCustomerFacade.class);

    private static final String NOT_FOUND_ACCOUNT_NUMBER = "9999999999";

    private final CustomerDtoConverter customerConverter = new CustomerDtoConverter();

    @Override
    public Optional<CustomerDto> getCustomer(String accountNumber) {
        LOGGER.info("get customer with account number " + accountNumber);

        if (NOT_FOUND_ACCOUNT_NUMBER.equals(accountNumber)) {
            LOGGER.info(String.format("customer with account number %s not found", accountNumber));
            return Optional.empty();
        }

        CustomerDto customer = new StubbedCustomerDto();
        LOGGER.info(String.format("returning customer %s", customerConverter.toJson(customer)));
        return Optional.of(customer);
    }

}
