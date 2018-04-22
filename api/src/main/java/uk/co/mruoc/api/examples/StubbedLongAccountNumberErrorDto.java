package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.InvalidAccountNumberFormatErrorDto;

public class StubbedLongAccountNumberErrorDto extends InvalidAccountNumberFormatErrorDto {

    public StubbedLongAccountNumberErrorDto() {
        super("99999999999");
    }

}
