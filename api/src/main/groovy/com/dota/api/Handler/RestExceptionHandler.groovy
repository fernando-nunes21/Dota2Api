package com.dota.api.Handler

import com.dota.api.Errors.HeroInvalidField
import com.dota.api.Errors.ErrorDetails
import com.dota.api.Errors.LimitExceeded
import com.dota.api.Errors.NotFoundHero
import com.dota.api.Errors.OffsetExceeded
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(NotFoundHero)
    ResponseEntity<ErrorDetails> handleNotFoundHero(NotFoundHero exception){
        ErrorDetails notFoundHeroDetails = ErrorDetails.Builder
                .newBuilder()
                .title("Heroi não encontrado na base de dados")
                .status(HttpStatus.NOT_FOUND.value())
                .errorMessage(exception.getMessage())
                .build()
        return new ResponseEntity(notFoundHeroDetails,HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HeroInvalidField)
    ResponseEntity<ErrorDetails> handleHeroInvalidFields(HeroInvalidField exception){
        ErrorDetails heroInvalidFieldsDetails = ErrorDetails.Builder
                .newBuilder()
                .title("Campos inválidos")
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(exception.getMessage())
                .build()
        return new ResponseEntity(heroInvalidFieldsDetails,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(LimitExceeded)
    ResponseEntity<ErrorDetails> handleLimitExceeded(LimitExceeded exception){
        ErrorDetails limitExceededDetails = ErrorDetails.Builder
                .newBuilder()
                .title("Limite máximo ultrapassado")
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(exception.getMessage())
                .build()
    }

    @ExceptionHandler(OffsetExceeded)
    ResponseEntity<ErrorDetails> handleOffsetExceeded(OffsetExceeded exception){
        ErrorDetails offsetExceededDetails = ErrorDetails.Builder
                .newBuilder()
                .title("Offset Máximo ultrapassado")
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(exception.getMessage())
                .build()
    }
}
