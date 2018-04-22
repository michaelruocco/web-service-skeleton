package uk.co.mruoc.app.mongo;

import java.util.Locale;

public class StubbedMongoAddress extends MongoAddress {

    public StubbedMongoAddress() {
        super("20 Seasame Street",
                "",
                "Northampton",
                "Northamptonshire",
                "NN4 0TJ",
                Locale.UK.getISO3Country());
    }

}
