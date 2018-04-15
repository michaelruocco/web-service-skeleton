package uk.co.mruoc.api;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDto {

    @ApiModelProperty(example = "error message")
    private String message;

    public ErrorDto() {
        // required by jackson
    }

    public ErrorDto(ErrorDtoBuilder builder) {
        this.message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    public static class ErrorDtoBuilder {

        private String message;

        public ErrorDtoBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(this);
        }

    }

}
