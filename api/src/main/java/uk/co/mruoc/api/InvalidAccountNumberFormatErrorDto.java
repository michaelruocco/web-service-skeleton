package uk.co.mruoc.api;

public class InvalidAccountNumberFormatErrorDto extends AbstractAccountNumberErrorDto {

    private static final int BAD_REQUEST = 400;
    private static final String MESSAGE = "invalid account number, should be 10 digit numeric: %s";

    public InvalidAccountNumberFormatErrorDto(String accountNumber) {
        super(BAD_REQUEST, MESSAGE, accountNumber);
    }

}
