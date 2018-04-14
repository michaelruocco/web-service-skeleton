package uk.co.mruoc.api;

import java.util.Locale;

public class StubbedAddressDto extends AddressDto {

    public StubbedAddressDto() {
        super(new AddressDtoBuilder()
                .setLine1("20 Seasame Street")
                .setLine2("")
                .setTown("Northampton")
                .setCounty("Northamptonshire")
                .setPostcode("NN4 0TJ")
                .setCountry(Locale.UK));
    }

}
