package uk.co.mruoc.api;

public class CustomerNotFoundErrorDto extends ErrorDto {

    private static final String MESSAGE = "customer with account number %s not found";

    public CustomerNotFoundErrorDto(String accountNumber) {
        super(new ErrorDtoBuilder().setMessage(String.format(MESSAGE, accountNumber)));
    }

}
