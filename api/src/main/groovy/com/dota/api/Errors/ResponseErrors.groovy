package com.dota.api.Errors

class ResponseErrors {

    private String errorMessage;

    ResponseErrors(String errorMessage){
        this.errorMessage = errorMessage
    }

    String getErrorMessage() {
        return errorMessage
    }
}
