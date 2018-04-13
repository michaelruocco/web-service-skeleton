package uk.co.mruoc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.AddressDto.AddressDtoBuilder;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDto.CustomerDtoBuilder;

import java.util.Locale;

@RestController
public class GetCustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCustomerController.class);

    @RequestMapping(value = "/customers/{accountNumber}", method = RequestMethod.GET)
    public CustomerDto getCustomer(@PathVariable String accountNumber) {
        LOGGER.info("get customer with account number " + accountNumber);
        AddressDto address = new AddressDtoBuilder()
                .setLine1("20 Tiffany Gardens")
                .setLine2("Hunsbury")
                .setTown("Northampton")
                .setCounty("Northamptonshire")
                .setPostCode("NN4 0TJ")
                .setCountry(Locale.UK.getISO3Country())
                .build();
        return new CustomerDtoBuilder()
                .setAccountNumber(accountNumber)
                .setFirstName("Michael")
                .setLastName("Ruocco")
                .addAddress(address)
                .build();

    }

}
