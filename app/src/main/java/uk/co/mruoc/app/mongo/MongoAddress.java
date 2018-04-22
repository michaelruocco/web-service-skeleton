package uk.co.mruoc.app.mongo;

import uk.co.mruoc.app.model.Address;

public class MongoAddress implements Address {

    private String line1;
    private String line2;
    private String town;
    private String county;
    private String postcode;
    private String country;

    public MongoAddress() {
        // intentionally blank
    }

    public MongoAddress(String line1,
                        String line2,
                        String town,
                        String county,
                        String postcode,
                        String country) {
        this.line1 = line1;
        this.line2 = line2;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
        this.country = country;
    }

    @Override
    public String getLine1() {
        return line1;
    }

    @Override
    public String getLine2() {
        return line2;
    }

    @Override
    public String getTown() {
        return town;
    }

    @Override
    public String getCounty() {
        return county;
    }

    @Override
    public String getPostcode() {
        return postcode;
    }

    @Override
    public String getCountry() {
        return country;
    }

}
