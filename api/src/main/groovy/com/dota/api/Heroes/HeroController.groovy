package com.dota.api.Heroes

import com.dota.api.Errors.InvalidHeroDifficult
import com.dota.api.Errors.InvalidHeroLane
import com.dota.api.Errors.NotFoundAnyHero
import com.dota.api.Errors.ResponseErrors
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
    ResponseEntity getHeroes() {
        try {
            List<Hero> heroes = heroesService.getHeroes()
            return new ResponseEntity(heroes, HttpStatus.OK)
        } catch (NotFoundAnyHero e) {
            return new ResponseEntity(new ResponseErrors(e.getMessage()), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/recommends")
    ResponseEntity getHeroRecommendation(@RequestParam("lane") String lane,
                                         @RequestParam("difficult") String difficult) {
        try{
            Hero hero = heroesService.getHeroRecommendation(lane, difficult)
            return new ResponseEntity(hero, HttpStatus.OK)
        } catch (NotFoundAnyHero e){
            return new ResponseEntity(new ResponseErrors(e.getMessage()),HttpStatus.NOT_FOUND)
        } catch (InvalidHeroLane | InvalidHeroDifficult e){
            return new ResponseEntity(new ResponseErrors(e.getMessage()),HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{id}/skins")
    ResponseEntity getHeroSkins(@PathVariable Integer id) {

    }

    @GetMapping("/{id}/skills")
    ResponseEntity getHeroSkills(@PathVariable Integer id) {

    }


    @PostMapping
    ResponseEntity createHero() {

    }

    @PutMapping("/{id}")
    ResponseEntity editHero(@PathVariable Integer id) {

    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteHero(@PathVariable Integer id) {

    }
}
