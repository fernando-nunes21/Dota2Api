package com.dota.api.Errors

class ErrorDetails {
    private String title
    private int status
    private String errorMessage

    ErrorDetails() {
    }

    String getTitle() {
        return title
    }

    int getStatus() {
        return status
    }

    String getErrorMessage() {
        return errorMessage
    }


    static final class Builder {
        private String title
        private int status
        private String errorMessage

        private Builder() {
        }

        static Builder newBuilder() {
            return new Builder();
        }

        Builder title(String title) { this.title = title; return this; }

        Builder status(int status) { this.status = status; return this; }

        Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage; return this;
        }

        ErrorDetails build() {
            ErrorDetails heroInvalidFieldsDetails = new ErrorDetails();
            heroInvalidFieldsDetails.errorMessage = this.errorMessage; heroInvalidFieldsDetails.title = this.title;
            heroInvalidFieldsDetails.status = this.status; return heroInvalidFieldsDetails;
        }
    }
}
