package uk.co.mruoc.api;

public class CustomerAlreadyExistsErrorDto extends AbstractAccountNumberErrorDto {

    private static final int SC_CONFLICT = 409;
    private static final String MESSAGE = "customer with account number %s already exists";

    public CustomerAlreadyExistsErrorDto(String accountNumber) {
        super(SC_CONFLICT, MESSAGE, accountNumber);
    }

}
