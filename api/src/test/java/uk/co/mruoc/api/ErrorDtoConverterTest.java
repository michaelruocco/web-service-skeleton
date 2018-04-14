package uk.co.mruoc.api;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Test;
import uk.co.mruoc.properties.ClasspathFileContentLoader;
import uk.co.mruoc.properties.FileContentLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ErrorDtoConverterTest {

    private final FileContentLoader contentLoader = new ClasspathFileContentLoader();
    private final ErrorDtoConverter converter = new ErrorDtoConverter();

    private final String errorJson = contentLoader.loadContent("/customer-not-found.json");

    @Test
    public void shouldConvertJsonToObject() {
        ErrorDto error = converter.toDto(errorJson);

        assertThat(error).isEqualToComparingFieldByFieldRecursively(new StubbedCustomerNotFoundErrorDto());
    }

    @Test
    public void shouldConvertObjectToJson() {
        ErrorDto error = new StubbedCustomerNotFoundErrorDto();

        String json = converter.toJson(error);

        assertThat(json).isEqualToIgnoringWhitespace(errorJson);
    }

    @Test
    public void shouldThrowExceptionIfJsonCannotBeParsed() {
        Throwable thrown = catchThrowable(() -> converter.toDto("{\"key\":\"value\"}"));

        assertThat(thrown)
                .isInstanceOf(CustomerDtoException.class)
                .hasCauseInstanceOf(UnrecognizedPropertyException.class)
                .hasMessageContaining("Unrecognized field \"key\"");
    }

}
