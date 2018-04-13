package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {

    private String line1;
    private String line2;
    private String town;
    private String county;
    private String postCode;
    private String country;

    public AddressDto() {
        // required by jackson
    }

    public AddressDto(AddressDtoBuilder builder) {
        this.line1 = builder.line1;
        this.line2 = builder.line2;
        this.town = builder.town;
        this.county = builder.county;
        this.postCode = builder.postCode;
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
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty
    public String getCountry() {
        return country;
    }

    public static class AddressDtoBuilder {

        private String line1;
        private String line2;
        private String town;
        private String county;
        private String postCode;
        private String country;

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

        public AddressDtoBuilder setPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public AddressDtoBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressDto build() {
            return new AddressDto(this);
        }

    }

}
