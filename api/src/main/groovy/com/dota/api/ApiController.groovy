package com.dota.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1")
class ApiController {

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    ResponseEntity getHeroes() {

    }

    @RequestMapping(value = "/skins", method = RequestMethod.GET)
    ResponseEntity getSkins() {

    }

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    ResponseEntity getSkills() {

    }

    @RequestMapping(value = "/heroes/{id}/skins", method = RequestMethod.GET)
    ResponseEntity getHeroSkins(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/heroes/{id}/skills", method = RequestMethod.GET)
    ResponseEntity getHeroSkills(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/heroes", method = RequestMethod.POST)
    ResponseEntity createHero() {

    }

    @RequestMapping(value = "/skins", method = RequestMethod.POST)
    ResponseEntity createSkin() {

    }

    @RequestMapping(value = "/skills", method = RequestMethod.POST)
    ResponseEntity createSkill() {

    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.PUT)
    ResponseEntity editHero(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/skins/{id}", method = RequestMethod.PUT)
    ResponseEntity editSkin(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/skills/{id}", method = RequestMethod.PUT)
    ResponseEntity editSkill(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.DELETE)
    ResponseEntity deleteHero(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/skins/{id}", method = RequestMethod.DELETE)
    ResponseEntity deleteSkin(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/skills/{id}", method = RequestMethod.DELETE)
    ResponseEntity deleteSkills(@PathVariable Integer id) {

    }
}
