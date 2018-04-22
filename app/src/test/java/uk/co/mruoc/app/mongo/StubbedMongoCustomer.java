package uk.co.mruoc.app.mongo;

import uk.co.mruoc.app.model.Address;

import java.util.Collections;
import java.util.List;

public class StubbedMongoCustomer extends MongoCustomer {

    public StubbedMongoCustomer() {
        super("1111111111",
                "Joe",
                "Bloggs",
                buildAddresses());
    }

    private static List<Address> buildAddresses() {
        return Collections.singletonList(new StubbedMongoAddress());
    }

}
