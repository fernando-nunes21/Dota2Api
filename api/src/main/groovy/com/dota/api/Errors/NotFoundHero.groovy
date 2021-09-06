package com.dota.api.Errors

class NotFoundHero extends RuntimeException{
    NotFoundHero(String message) {
        super(message)
    }
}
