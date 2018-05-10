package uk.co.mruoc.app.model;

import java.util.Locale;

public class StubbedAddress extends DefaultAddress {

    public StubbedAddress() {
        super("20 Seasame Street",
                "",
                "Northampton",
                "Northamptonshire",
                "NN4 0TJ",
                Locale.UK.getISO3Country());
    }

}