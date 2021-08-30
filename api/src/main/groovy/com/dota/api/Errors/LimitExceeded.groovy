package com.dota.api.Errors

class LimitExceeded extends RuntimeException{
    LimitExceeded(String message) {
        super(message)
    }
}
