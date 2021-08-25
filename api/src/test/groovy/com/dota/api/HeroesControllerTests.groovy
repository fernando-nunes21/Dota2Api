package com.dota.api

import com.dota.api.Heroes.HeroesController
import com.fasterxml.jackson.databind.ObjectMapper
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
    private ObjectMapper objectMapper

    HeroesControllerTests(ObjectMapper objectMapper){
        this.objectMapper = objectMapper
    }

    def "Get Heroes should return a list of heroes and status code 200"(){
        given:

        when:
        ResultActions resultActions = mockMvc.perform(get("/v1/heroes"))
        MvcResult contentResult = resultActions.andReturn()
        String response = contentResult.getResponse().getContentAsString()
        List<Heroes> heroes = objectMapper.readValue(response,List<Heroes>.class)

        then:
        resultActions.andExpect(status().isOk())
        heroes.size() > 0

    }

}
