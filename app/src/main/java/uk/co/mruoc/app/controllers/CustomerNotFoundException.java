package uk.co.mruoc.app.controllers;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String accountNumber) {
        super(accountNumber);
    }

}
