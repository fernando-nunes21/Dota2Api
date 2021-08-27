package com.dota.api

import com.dota.api.Errors.InvalidHeroDifficult
import com.dota.api.Errors.InvalidHeroLane
import com.dota.api.Errors.NotFoundAnyHero

import com.dota.api.Heroes.HeroController
import com.dota.api.Heroes.HeroService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification

class HeroControllerTests extends Specification {

    private HeroService heroService = Mock(HeroService)
    private HeroController heroController = new HeroController(heroService)
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(heroController).build()

    def "Get Heroes should return a status code 200 when returns list of heroes"() {
        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isOk())

    }

    def "Get Heroes should return a status code 404 and error body message when not found any hero"() {
        given:
        this.heroService.getHeroes() >> {
            throw new NotFoundAnyHero("Nenhum heroi foi encontrado na base de dados")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes"))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nenhum heroi foi encontrado na base de dados'}"))
    }

    def "Get a hero recommend should return a status code 200 when params are correctly"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "safe")
        params.add("difficult", "easy")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/recommends").params(params))

        then:
        response.andExpect(status().isOk())
    }

    def "Get a hero recommend should return a status code 404 when not found a hero in that lane and difficult "() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "safe")
        params.add("difficult", "easy")
        this.heroService.getHeroRecommendation("safe", "easy") >> {
            throw new NotFoundAnyHero("Nao foi encontrado nenhum heroi nesta lane com essa dificuldade")
        }
        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/recommends").params(params))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nao foi encontrado nenhum heroi nesta lane com " +
                "essa dificuldade'}"))
    }

    def "Get a hero recommend should return 400 when informed lane is invalid"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "low")
        params.add("difficult", "easy")
        this.heroService.getHeroRecommendation("low", "easy") >> {
            throw new InvalidHeroLane("A lane informada do heroi nao existe. As lanes existentes são: safe, off e mid")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/recommends").params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'A lane informada do heroi nao existe. " +
                "As lanes existentes são: safe, off e mid'}"))
    }

    def "Get hero recommend should return 400 when difficult is not valid"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "off")
        params.add("difficult", "overkill")
        this.heroService.getHeroRecommendation("off", "overkill") >> {
            throw new InvalidHeroDifficult("A dificuldade informada do heroi nao existe. As dificuldades existentes" +
                    " são: easy, medium e hard")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/recommends").params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'A dificuldade informada do heroi nao existe." +
                " As dificuldades existentes são: easy, medium e hard'}"))
    }

    def "Get a hero skins should return 200 when path param is valid"() {
        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1))

        then:
        response.andExpect(status().isOk())
    }

    def "Get a hero skins should return 404 when not found a hero with the informed id"() {
        given:
        this.heroService.getHeroSkins(1) >> {
            throw new NotFoundAnyHero("Nao foi encontrado nenhum heroi com o id informado")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nao foi encontrado nenhum heroi com o id " +
                "informado'}"))

    }

}
