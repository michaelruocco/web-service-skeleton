package uk.co.mruoc.app.model;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String accountNumber) {
        super(accountNumber);
    }

}
