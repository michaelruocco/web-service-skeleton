package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

public class AddressDto {

    private String line1;
    private String line2;
    private String town;
    private String county;
    private String postcode;
    private Locale country;

    public AddressDto() {
        // required by jackson
    }

    public AddressDto(AddressDtoBuilder builder) {
        this.line1 = builder.line1;
        this.line2 = builder.line2;
        this.town = builder.town;
        this.county = builder.county;
        this.postcode = builder.postcode;
        this.country = builder.country;
    }

    @JsonProperty
    public String getLine1() {
        return line1;
    }

    @JsonProperty
    public String getLine2() {
        return line2;
    }

    @JsonProperty
    public String getTown() {
        return town;
    }

    @JsonProperty
    public String getCounty() {
        return county;
    }

    @JsonProperty
    public String getPostcode() {
        return postcode;
    }

    @JsonProperty
    public Locale getCountry() {
        return country;
    }

    public static class AddressDtoBuilder {

        private String line1;
        private String line2;
        private String town;
        private String county;
        private String postcode;
        private Locale country;

        public AddressDtoBuilder setLine1(String line1) {
            this.line1 = line1;
            return this;
        }

        public AddressDtoBuilder setLine2(String line2) {
            this.line2 = line2;
            return this;
        }

        public AddressDtoBuilder setTown(String town) {
            this.town = town;
            return this;
        }

        public AddressDtoBuilder setCounty(String county) {
            this.county = county;
            return this;
        }

        public AddressDtoBuilder setPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public AddressDtoBuilder setCountry(Locale country) {
            this.country = country;
            return this;
        }

        public AddressDto build() {
            return new AddressDto(this);
        }

    }

}
