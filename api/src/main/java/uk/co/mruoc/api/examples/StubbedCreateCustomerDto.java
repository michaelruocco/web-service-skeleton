package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;

import java.util.Collections;
import java.util.List;

public class StubbedCreateCustomerDto extends CustomerDto {

    public StubbedCreateCustomerDto() {
        super(new CustomerDtoBuilder()
                .setAccountNumber("2222222222")
                .setFirstName("Homer")
                .setLastName("Simpson")
                .setAddresses(buildAddresses()));
    }

    private static List<AddressDto> buildAddresses() {
        return Collections.singletonList(new StubbedAddressDto2());
    }

}
