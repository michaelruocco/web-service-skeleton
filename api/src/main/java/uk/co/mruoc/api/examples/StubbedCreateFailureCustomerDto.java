package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;

import java.util.Collections;
import java.util.List;

public class StubbedCreateFailureCustomerDto extends CustomerDto {

    public StubbedCreateFailureCustomerDto() {
        super(new CustomerDtoBuilder()
                .setAccountNumber("222abc")
                .setFirstName("Invalid")
                .setLastName("AccountNumber")
                .setAddresses(buildAddresses()));
    }

    private static List<AddressDto> buildAddresses() {
        return Collections.singletonList(new StubbedAddressDto2());
    }

}
