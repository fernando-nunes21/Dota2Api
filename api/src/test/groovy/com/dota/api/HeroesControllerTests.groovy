package com.dota.api

import com.dota.api.Heroes.HeroesController
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification


class HeroesControllerTests extends Specification {

    private HeroesController heroesController = new HeroesController()
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(heroesController).build()

    def "Get Heroes should return a status code 200 when everything is normal"(){
        given:

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isOk())

    }

    def "Get Heroes should return a status code 404 and throw exception when not found any hero"(){
        given:

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isNotFound())
        thrown(NotFoundAnyHero)
    }

}
