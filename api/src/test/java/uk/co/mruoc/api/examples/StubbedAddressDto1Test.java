package uk.co.mruoc.api.examples;

import org.junit.Test;
import uk.co.mruoc.api.AddressDto;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedAddressDto1Test {

    private final AddressDto address = new StubbedAddressDto1();

    @Test
    public void shouldReturnLine1() {
        assertThat(address.getLine1()).isEqualTo("20 Seasame Street");
    }

    @Test
    public void shouldReturnLine2() {
        assertThat(address.getLine2()).isEmpty();
    }

    @Test
    public void shouldReturnTown() {
        assertThat(address.getTown()).isEqualTo("Northampton");
    }

    @Test
    public void shouldReturnCounty() {
        assertThat(address.getCounty()).isEqualTo("Northamptonshire");
    }

    @Test
    public void shouldReturnPostcode() {
        assertThat(address.getPostcode()).isEqualTo("NN4 0TJ");
    }

    @Test
    public void shouldReturnCountry() {
        assertThat(address.getCountry()).isEqualTo(Locale.UK.getISO3Country());
    }

}
