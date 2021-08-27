package com.dota.api.Errors

class ResponseError {

    private String errorMessage;

    ResponseError(String errorMessage){
        this.errorMessage = errorMessage
    }

    String getErrorMessage() {
        return errorMessage
    }
}
