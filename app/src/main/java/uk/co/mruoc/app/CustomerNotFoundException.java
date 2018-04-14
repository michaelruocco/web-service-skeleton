package uk.co.mruoc.app;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String accountNumber) {
        super(accountNumber);
    }

}
