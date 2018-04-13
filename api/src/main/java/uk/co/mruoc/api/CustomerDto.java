package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {

    private String email;
    private String firstName;
    private String lastName;
    private List<AddressDto> addresses;

    public CustomerDto() {
        // required by jackson
    }

    public CustomerDto(CustomerDtoBuilder builder) {
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.addresses = builder.addresses;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public static class CustomerDtoBuilder {

        private String email;
        private String firstName;
        private String lastName;
        private List<AddressDto> addresses = new ArrayList<>();

        public CustomerDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerDtoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerDtoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerDtoBuilder addAddress(AddressDto address) {
            this.addresses.add(address);
            return this;
        }

        public CustomerDtoBuilder setAddresses(List<AddressDto> addresses) {
            this.addresses = addresses;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(this);
        }

    }

}
