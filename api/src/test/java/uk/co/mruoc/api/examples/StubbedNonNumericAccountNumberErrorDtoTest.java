package uk.co.mruoc.api.examples;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedNonNumericAccountNumberErrorDtoTest {

    private final StubbedNonNumericAccountNumberErrorDto error = new StubbedNonNumericAccountNumberErrorDto();

    @Test
    public void shouldReturnAccountNumberShorterThanTenDigits() {
        assertThat(error.getAccountNumber()).isEqualTo("999999999a");
    }

}