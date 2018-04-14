package uk.co.mruoc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.StubbedCustomerDto;

@RestController
public class GetCustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCustomerController.class);

    @RequestMapping(value = "/customers/{accountNumber}", method = RequestMethod.GET)
    public CustomerDto getCustomer(@PathVariable String accountNumber) {
        LOGGER.info("get customer with account number " + accountNumber);
        return new StubbedCustomerDto();
    }

}
