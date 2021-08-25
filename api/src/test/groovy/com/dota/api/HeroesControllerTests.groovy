package com.dota.api

import com.dota.api.Errors.NotFoundAnyHero

import com.dota.api.Heroes.HeroesController
import com.dota.api.Heroes.HeroesService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification


class HeroesControllerTests extends Specification {

    private HeroesService heroesService = Mock(HeroesService)
    private HeroesController heroesController = new HeroesController(heroesService)
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(heroesController).build()

    def "Get Heroes should return a status code 200 when everything is normal"(){
        given:

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isOk())

    }

    def "Get Heroes should return a status code 404 when not found any hero"(){
        given:
        this.heroesService.getHeroes() >> {
            throw new NotFoundAnyHero("Nenhum heroi foi encontrado na base de dados")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isNotFound())

    }

}
