package uk.co.mruoc.app.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.app.facade.CustomerFacade;
import uk.co.mruoc.app.facade.DefaultCustomerFacade;

import java.util.Optional;

@RestController
public class GetCustomerController {

    private final CustomerFacade facade;

    public GetCustomerController() {
        this(new DefaultCustomerFacade());
    }

    public GetCustomerController(CustomerFacade facade) {
        this.facade = facade;
    }

    @RequestMapping(value = "/customers/{accountNumber}", method = RequestMethod.GET)
    public CustomerDto getCustomer(@PathVariable String accountNumber) {
        Optional<CustomerDto> customer = facade.getCustomer(accountNumber);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new CustomerNotFoundException(accountNumber);
    }

}
