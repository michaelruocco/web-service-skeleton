package uk.co.mruoc.app.model;

import java.util.Collections;
import java.util.List;

public class StubbedCustomer extends DefaultCustomer {

    public StubbedCustomer() {
        super("1111111111",
                "Joe",
                "Bloggs",
                buildAddresses());
    }

    private static List<Address> buildAddresses() {
        return Collections.singletonList(new StubbedAddress());
    }

}