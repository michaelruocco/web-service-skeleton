package uk.co.mruoc.app;

import org.assertj.core.api.AbstractAssert;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.app.model.Address;

import java.util.Objects;

public class AddressAssert extends AbstractAssert<AddressAssert, Address> {

    public AddressAssert(Address actual) {
        super(actual, AddressAssert.class);
    }

    public AddressAssert isEqualTo(AddressDto dto) {
        return hasLine1(dto.getLine1())
                .hasLine2(dto.getLine2())
                .hasTown(dto.getTown())
                .hasCounty(dto.getCounty())
                .hasPostcode(dto.getPostcode())
                .hasCountry(dto.getCountry());
    }

    public static AddressAssert assertThat(Address actual) {
        return new AddressAssert(actual);
    }

    public AddressAssert hasLine1(String line1) {
        isNotNull();
        String actualLine1 = actual.getLine1();
        if (!Objects.equals(actualLine1, line1)) {
            failWithMessage("Expected address line1 to be <%s> but was <%s>", line1, actualLine1);
        }
        return this;
    }

    public AddressAssert hasLine2(String line2) {
        isNotNull();
        String actualLine2 = actual.getLine2();
        if (!Objects.equals(actualLine2, line2)) {
            failWithMessage("Expected address line2 to be <%s> but was <%s>", line2, actualLine2);
        }
        return this;
    }

    public AddressAssert hasTown(String town) {
        isNotNull();
        String actualTown = actual.getTown();
        if (!Objects.equals(actualTown, town)) {
            failWithMessage("Expected address town to be <%s> but was <%s>", town, actualTown);
        }
        return this;
    }

    public AddressAssert hasCounty(String county) {
        isNotNull();
        String actualCounty = actual.getCounty();
        if (!Objects.equals(actualCounty, county)) {
            failWithMessage("Expected address county to be <%s> but was <%s>", county, actualCounty);
        }
        return this;
    }

    public AddressAssert hasPostcode(String postcode) {
        isNotNull();
        String actualPostcode = actual.getPostcode();
        if (!Objects.equals(actualPostcode, postcode)) {
            failWithMessage("Expected address postcode to be <%s> but was <%s>", postcode, actualPostcode);
        }
        return this;
    }

    public AddressAssert hasCountry(String country) {
        isNotNull();
        String actualCountry = actual.getCountry();
        if (!Objects.equals(actualCountry, country)) {
            failWithMessage("Expected address country to be <%s> but was <%s>", country, actualCountry);
        }
        return this;
    }

}
