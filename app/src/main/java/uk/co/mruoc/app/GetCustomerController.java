package uk.co.mruoc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDtoConverter;
import uk.co.mruoc.api.StubbedCustomerDto;

@RestController
public class GetCustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCustomerController.class);

    private static final String NOT_FOUND_ACCOUNT_NUMBER = "9999999999";

    private final CustomerDtoConverter customerConverter = new CustomerDtoConverter();

    @RequestMapping(value = "/customers/{accountNumber}", method = RequestMethod.GET)
    public CustomerDto getCustomer(@PathVariable String accountNumber) {
        LOGGER.info("get customer with account number " + accountNumber);

        if (NOT_FOUND_ACCOUNT_NUMBER.equals(accountNumber)) {
            LOGGER.info(String.format("customer with account number %s not found", accountNumber));
            throw new CustomerNotFoundException(accountNumber);
        }

        CustomerDto customer = new StubbedCustomerDto();
        LOGGER.info(String.format("returning customer %s", customerConverter.toJson(customer)));
        return customer;
    }

}
