package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.CustomerDto;

import java.util.Collections;
import java.util.List;

public class StubbedCustomerDto1 extends CustomerDto {

    public StubbedCustomerDto1() {
        super(new CustomerDtoBuilder()
                .setAccountNumber("1111111111")
                .setFirstName("Joe")
                .setLastName("Bloggs")
                .setAddresses(buildAddresses()));
    }

    private static List<AddressDto> buildAddresses() {
        return Collections.singletonList(new StubbedAddressDto1());
    }

}
