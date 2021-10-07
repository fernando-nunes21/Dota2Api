package com.dota.api.Heroes


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
    ResponseEntity<?> getHeroes(@RequestParam(name = "lane", required = false) String lane,
                                @RequestParam(name = "difficult", required = false) String difficult,
                                @RequestParam(name = "offset", required = true) Integer offset,
                                @RequestParam(name = "limit", required = true) Integer limit) {
        List<Hero> heroes = heroesService.getHeroes(lane, difficult, offset, limit)
        return new ResponseEntity(heroes, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<?> createHero(@RequestBody Hero hero) {
        heroesService.createHero(hero)
        return new ResponseEntity(new CrudResponses("O heroi foi criado com sucesso"), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    ResponseEntity<?> editHero(@PathVariable Integer id, @RequestBody Hero hero) {
        heroesService.editHero(id, hero)
        return new ResponseEntity(new CrudResponses("Editado com sucesso"), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteHero(@PathVariable Integer id) {
        heroesService.deleteHero(id)
        return new ResponseEntity(new CrudResponses("Heroi deletado com sucesso"), HttpStatus.OK)
    }
}
