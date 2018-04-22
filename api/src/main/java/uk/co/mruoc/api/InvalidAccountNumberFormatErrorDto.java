package uk.co.mruoc.api;

public class InvalidAccountNumberFormatErrorDto extends AbstractAccountNumberErrorDto {

    private static final int SC_BAD_REQUEST = 400;
    private static final String MESSAGE = "invalid account number, should be 10 digit numeric: %s";

    public InvalidAccountNumberFormatErrorDto(String accountNumber) {
        super(SC_BAD_REQUEST, MESSAGE, accountNumber);
    }

}
