package com.dota.api.Heroes

import com.dota.api.Errors.HeroInvalidFields
import com.dota.api.Errors.InvalidHeroDifficult
import com.dota.api.Errors.InvalidHeroLane
import com.dota.api.Errors.LimitExceeded
import com.dota.api.Errors.NotFoundHero
import com.dota.api.Errors.OffsetExceeded
import com.dota.api.Errors.ResponseError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/heroes")
class HeroController {

    private HeroService heroesService

    HeroController(HeroService heroesService) {
        this.heroesService = heroesService
    }

    @GetMapping
    ResponseEntity getHeroes(@RequestParam("lane") String lane,
                             @RequestParam("difficult") String difficult,
                             @RequestParam("recommend") Boolean recommend,
                             @RequestParam("offset") Integer offset,
                             @RequestParam("limit") Integer limit) {
        try {
            List<Hero> heroes = heroesService.getHeroes(lane, difficult, recommend, offset, limit)
            return new ResponseEntity(heroes, HttpStatus.OK)
        } catch (NotFoundHero e) {
            return new ResponseEntity(new ResponseError(e.getMessage()), HttpStatus.NOT_FOUND)
        } catch (LimitExceeded | OffsetExceeded | InvalidHeroLane | InvalidHeroDifficult e) {
            return new ResponseEntity(new ResponseError(e.getMessage()), HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping
    ResponseEntity createHero() {
        try {
            heroesService.createHero()
            return new ResponseEntity(new CrudResponses("O heroi foi criado com sucesso"), HttpStatus.OK)
        } catch (HeroInvalidFields e) {
            return new ResponseEntity(new ResponseError(e.getMessage()), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/{id}")
    ResponseEntity editHero(@PathVariable Integer id) {
        try {
            heroesService.editHero(id)
            return new ResponseEntity(new CrudResponses("Editado com sucesso"), HttpStatus.OK)

        } catch (NotFoundHero e) {
            return new ResponseEntity(new ResponseError(e.getMessage()), HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteHero(@PathVariable Integer id) {
        try {
            heroesService.deleteHero(id)
            return new ResponseEntity(new CrudResponses("Heroi deletado com sucesso"), HttpStatus.OK)
        } catch (NotFoundHero e) {
            return new ResponseEntity(new ResponseError(e.getMessage()), HttpStatus.NOT_FOUND)
        }
    }
}