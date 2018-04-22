package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.InvalidAccountNumberFormatErrorDto;

public class StubbedShortAccountNumberErrorDto extends InvalidAccountNumberFormatErrorDto {

    public StubbedShortAccountNumberErrorDto() {
        super("999999999");
    }

}
