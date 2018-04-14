package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerNotFoundErrorDto extends ErrorDto {

    private static final String MESSAGE = "customer with account number %s not found";

    private final String accountNumber;

    public CustomerNotFoundErrorDto(String accountNumber) {
        super(new ErrorDtoBuilder().setMessage(String.format(MESSAGE, accountNumber)));
        this.accountNumber = accountNumber;
    }

    @JsonIgnore
    public String getAccountNumber() {
        return accountNumber;
    }

}
