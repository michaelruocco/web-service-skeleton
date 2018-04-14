package uk.co.mruoc.api;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Test;
import uk.co.mruoc.properties.ClasspathFileContentLoader;
import uk.co.mruoc.properties.FileContentLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CustomerDtoConverterTest {

    private final FileContentLoader contentLoader = new ClasspathFileContentLoader();
    private final CustomerDtoConverter converter = new CustomerDtoConverter();

    private final String customerJson = contentLoader.loadContent("/stubbed-customer.json");

    @Test
    public void shouldConvertJsonToObject() {
        CustomerDto customer = converter.toCustomer(customerJson);

        assertThat(customer).isEqualToComparingFieldByFieldRecursively(new StubbedCustomerDto());
    }

    @Test
    public void shouldConvertObjectToJson() {
        CustomerDto customer = new StubbedCustomerDto();

        String json = converter.toJson(customer);

        assertThat(json).isEqualToIgnoringWhitespace(customerJson);
    }

    @Test
    public void shouldThrowExceptionIfJsonCannotBeParsed() {
        Throwable thrown = catchThrowable(() -> converter.toCustomer("{\"key\":\"value\"}"));

        assertThat(thrown)
                .isInstanceOf(CustomerDtoException.class)
                .hasCauseInstanceOf(UnrecognizedPropertyException.class)
                .hasMessageContaining("Unrecognized field \"key\"");
    }

}
