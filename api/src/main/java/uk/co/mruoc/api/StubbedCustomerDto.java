package uk.co.mruoc.api;

import java.util.Collections;
import java.util.List;

public class StubbedCustomerDto extends CustomerDto {

    public StubbedCustomerDto() {
        super(new CustomerDtoBuilder()
                .setAccountNumber("1111111111")
                .setFirstName("Joe")
                .setLastName("Bloggs")
                .setAddresses(buildAddresses()));
    }

    private static List<AddressDto> buildAddresses() {
        return Collections.singletonList(new StubbedAddressDto());
    }

}
