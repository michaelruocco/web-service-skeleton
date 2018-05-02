package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractAccountNumberErrorDto extends ErrorDto {

    private final String accountNumber;

    public AbstractAccountNumberErrorDto(int statusCode, String messageFormat, String accountNumber) {
        super(new ErrorDtoBuilder()
                .setStatusCode(statusCode)
                .setMessage(String.format(messageFormat, accountNumber)));
        this.accountNumber = accountNumber;
    }

    @JsonIgnore
    public String getAccountNumber() {
        return accountNumber;
    }

}
