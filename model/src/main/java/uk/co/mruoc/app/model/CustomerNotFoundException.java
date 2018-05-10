package uk.co.mruoc.app.model;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String accountNumber) {
        super(accountNumber);
    }

}
