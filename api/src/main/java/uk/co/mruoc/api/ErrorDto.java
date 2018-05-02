package uk.co.mruoc.api;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDto {

    @ApiModelProperty(example = "error message")
    private String message;

    @ApiModelProperty(example = "400")
    private int statusCode;

    public ErrorDto() {
        // required by jackson
    }

    public ErrorDto(ErrorDtoBuilder builder) {
        this.message = builder.message;
        this.statusCode = builder.statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static class ErrorDtoBuilder {

        private String message;
        private int statusCode;

        public ErrorDtoBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorDtoBuilder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(this);
        }

    }

}
