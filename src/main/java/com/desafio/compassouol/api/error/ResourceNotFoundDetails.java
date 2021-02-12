package com.desafio.compassouol.api.error;

public class ResourceNotFoundDetails extends ErrorDetails{

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setStatus_message(status_message);
            return resourceNotFoundDetails;
        }
    }
}
