package com.dota.api

import com.dota.api.Errors.NotFoundAnyHero

import com.dota.api.Heroes.HeroesController
import com.dota.api.Heroes.HeroesService
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification

class HeroesControllerTests extends Specification {

    private HeroesService heroesService = Mock(HeroesService)
    private HeroesController heroesController = new HeroesController(heroesService)
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(heroesController).build()

    def "Get Heroes should return a status code 200 when returns list of heroes"() {
        given:

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isOk())

    }

    def "Get Heroes should return a status code 404 and error body message when not found any hero"() {
        given:
        this.heroesService.getHeroes() >> {
            throw new NotFoundAnyHero("Nenhum heroi foi encontrado na base de dados")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nenhum heroi foi encontrado na base de dados'}"))

    }

}
