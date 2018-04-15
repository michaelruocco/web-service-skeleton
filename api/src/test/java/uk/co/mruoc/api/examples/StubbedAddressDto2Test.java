package uk.co.mruoc.api.examples;

import org.junit.Test;
import uk.co.mruoc.api.AddressDto;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedAddressDto2Test {

    private final AddressDto address = new StubbedAddressDto2();

    @Test
    public void shouldReturnLine1() {
        assertThat(address.getLine1()).isEqualTo("The O2 Arena");
    }

    @Test
    public void shouldReturnLine2() {
        assertThat(address.getLine2()).isEqualTo("Peninsula Square");
    }

    @Test
    public void shouldReturnTown() {
        assertThat(address.getTown()).isEqualTo("London");
    }

    @Test
    public void shouldReturnCounty() {
        assertThat(address.getCounty()).isEqualTo("");
    }

    @Test
    public void shouldReturnPostcode() {
        assertThat(address.getPostcode()).isEqualTo("SE10 0DX");
    }

    @Test
    public void shouldReturnCountry() {
        assertThat(address.getCountry()).isEqualTo(Locale.UK.getISO3Country());
    }

}
