package com.dota.api.Errors

class NotFoundAnyHero extends RuntimeException{
    NotFoundAnyHero(String message) {
        super(message)
    }
}
