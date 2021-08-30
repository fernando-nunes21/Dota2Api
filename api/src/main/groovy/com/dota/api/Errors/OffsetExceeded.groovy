package com.dota.api.Errors

class OffsetExceeded extends RuntimeException{
    OffsetExceeded(String message) {
        super(message)
    }
}
