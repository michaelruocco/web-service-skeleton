package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class CustomerDto {

    @ApiModelProperty(example = "1111111111")
    @Pattern(regexp = Regex.ACCOUNT_NUMBER)
    private String accountNumber;

    @ApiModelProperty(example = "Joe")
    private String firstName;

    @ApiModelProperty(example = "Bloggs")
    private String lastName;

    private List<AddressDto> addresses;

    public CustomerDto() {
        // required by jackson
    }

    public CustomerDto(CustomerDtoBuilder builder) {
        this.accountNumber = builder.accountNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.addresses = builder.addresses;
    }

    @JsonProperty
    public String getAccountNumber() {
        return accountNumber;
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

    @JsonIgnore
    public AddressDto getAddress(int index) {
        return addresses.get(index);
    }

    public static class CustomerDtoBuilder {

        private String accountNumber;
        private String firstName;
        private String lastName;
        private List<AddressDto> addresses = new ArrayList<>();

        public CustomerDtoBuilder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
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
