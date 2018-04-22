package uk.co.mruoc.app.model;

import java.util.List;

public interface Customer {
    String getAccountNumber();

    String getFirstName();

    String getLastName();

    List<Address> getAddresses();
}
