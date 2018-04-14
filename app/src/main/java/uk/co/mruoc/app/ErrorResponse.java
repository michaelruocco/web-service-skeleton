package uk.co.mruoc.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.api.ErrorDto;

public class ErrorResponse extends ResponseEntity<ErrorDto> {

    public ErrorResponse(ErrorDto error, HttpStatus status) {
        super(error, status);
    }

    public String getMessage() {
        ErrorDto error = getBody();
        return error.getMessage();
    }

    public int getStatusCodeValue() {
        return getStatusCode().value();
    }

}
