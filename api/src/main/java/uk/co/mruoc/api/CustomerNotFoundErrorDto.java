package uk.co.mruoc.api;

public class CustomerNotFoundErrorDto extends AbstractAccountNumberErrorDto {

    private static final int SC_NOT_FOUND = 404;
    private static final String MESSAGE = "customer with account number %s not found";

    public CustomerNotFoundErrorDto(String accountNumber) {
        super(SC_NOT_FOUND, MESSAGE, accountNumber);
    }

}
