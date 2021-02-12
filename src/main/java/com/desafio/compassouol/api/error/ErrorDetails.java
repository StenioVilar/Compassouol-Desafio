package com.desafio.compassouol.api.error;

public class ErrorDetails {

    private String status_message;

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public static final class Builder {
        private String status_message;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status_message(String status_message) {
            this.status_message = status_message;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setStatus_message(status_message);
            return errorDetails;
        }
    }

}
