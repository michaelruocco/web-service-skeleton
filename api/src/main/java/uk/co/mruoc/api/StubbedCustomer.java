package uk.co.mruoc.api;

import uk.co.mruoc.api.AddressDto.AddressDtoBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StubbedCustomer extends CustomerDto {

    public StubbedCustomer() {
        super(new CustomerDtoBuilder()
                .setAccountNumber("1111111111")
                .setFirstName("Michael")
                .setLastName("Ruocco")
                .setAddresses(buildAddresses()));
    }

    private static List<AddressDto> buildAddresses() {
        return Collections.singletonList(new AddressDtoBuilder()
                .setLine1("20 Tiffany Gardens")
                .setLine2("Hunsbury")
                .setTown("Northampton")
                .setCounty("Northamptonshire")
                .setPostCode("NN4 0TJ")
                .setCountry(Locale.UK.getISO3Country())
                .build());
    }
}
