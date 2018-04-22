package uk.co.mruoc.api.examples;

import uk.co.mruoc.api.InvalidAccountNumberFormatErrorDto;

public class StubbedNonNumericAccountNumberErrorDto extends InvalidAccountNumberFormatErrorDto {

    public StubbedNonNumericAccountNumberErrorDto() {
        super("999999999a");
    }

}
