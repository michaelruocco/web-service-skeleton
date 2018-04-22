package uk.co.mruoc.api.examples;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedShortAccountNumberErrorDtoTest {

    private final StubbedShortAccountNumberErrorDto error = new StubbedShortAccountNumberErrorDto();

    @Test
    public void shouldReturnAccountNumberShorterThanTenDigits() {
        assertThat(error.getAccountNumber()).isEqualTo("999999999");
    }

}