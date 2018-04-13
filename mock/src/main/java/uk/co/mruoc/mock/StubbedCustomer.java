package uk.co.mruoc.mock;

import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.AddressDto.AddressDtoBuilder;
import uk.co.mruoc.api.CustomerDto;

import java.util.Arrays;
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
        return Arrays.asList(new AddressDtoBuilder()
                .setLine1("20 Tiffany Gardens")
                .setLine2("Hunsbury")
                .setTown("Northampton")
                .setCounty("Northamptonshire")
                .setPostCode("NN4 0TJ")
                .setCountry(Locale.UK.getISO3Country())
                .build());
    }
}
