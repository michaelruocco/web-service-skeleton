package uk.co.mruoc.api.examples;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedLongAccountNumberErrorDtoTest {

    private final StubbedLongAccountNumberErrorDto error = new StubbedLongAccountNumberErrorDto();

    @Test
    public void shouldReturnAccountNumberLongerThanTenDigits() {
        assertThat(error.getAccountNumber()).isEqualTo("99999999999");
    }

}