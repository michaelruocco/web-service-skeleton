package uk.co.mruoc.app.model;

import uk.co.mruoc.api.AddressDto;

public class Address {

    private String line1;
    private String line2;
    private String town;
    private String county;
    private String postcode;
    private String country;

    public Address() {
        // intentionally blank
    }

    public Address(String line1,
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

    public Address(AddressDto dto) {
        this(dto.getLine1(),
                dto.getLine2(),
                dto.getTown(),
                dto.getCounty(),
                dto.getPostcode(),
                dto.getCountry());
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

}
