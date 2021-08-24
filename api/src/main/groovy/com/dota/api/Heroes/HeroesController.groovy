package com.dota.api.Heroes

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
class HeroesController {

    @GetMapping
    ResponseEntity getHeroes() {

    }

    @GetMapping
    ResponseEntity getHeroRecommendation(@RequestParam("lane") String lane,
                                         @RequestParam("difficult") String difficult) {

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
