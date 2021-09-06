package com.dota.api.Errors

class InvalidHeroLane extends RuntimeException{
    InvalidHeroLane(String message) {
        super(message)
    }
}
