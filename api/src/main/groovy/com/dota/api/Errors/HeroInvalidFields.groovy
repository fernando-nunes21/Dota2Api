package com.dota.api.Errors

class HeroInvalidFields extends RuntimeException{
    HeroInvalidFields(String message) {
        super(message)
    }
}

