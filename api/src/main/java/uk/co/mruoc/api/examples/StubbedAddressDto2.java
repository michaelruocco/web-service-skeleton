package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.AddressDto;

import java.util.Locale;

public class StubbedAddressDto2 extends AddressDto {

    public StubbedAddressDto2() {
        super(new AddressDtoBuilder()
                .setLine1("The O2 Arena")
                .setLine2("Peninsula Square")
                .setTown("London")
                .setCounty("")
                .setPostcode("SE10 0DX")
                .setCountry(Locale.UK.getISO3Country()));
    }

}
