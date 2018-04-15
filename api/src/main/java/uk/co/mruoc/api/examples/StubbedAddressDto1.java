package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.AddressDto;

import java.util.Locale;

public class StubbedAddressDto1 extends AddressDto {

    public StubbedAddressDto1() {
        super(new AddressDtoBuilder()
                .setLine1("20 Seasame Street")
                .setLine2("")
                .setTown("Northampton")
                .setCounty("Northamptonshire")
                .setPostcode("NN4 0TJ")
                .setCountry(Locale.UK.getISO3Country()));
    }

}
