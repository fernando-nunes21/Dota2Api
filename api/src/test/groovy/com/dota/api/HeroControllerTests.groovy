package com.dota.api

import com.dota.api.Errors.InvalidHeroDifficult
import com.dota.api.Errors.InvalidHeroLane
import com.dota.api.Errors.LimitExceeded
import com.dota.api.Errors.NotFoundHero
import com.dota.api.Errors.OffsetExceeded
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
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "")
        params.add("difficult", "")
        params.add("recommend", "false")
        params.add("offset", "0")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isOk())

    }

    def "Get Heroes should return a status code 404 and error body message when not found heroes"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "")
        params.add("difficult", "")
        params.add("recommend", "false")
        params.add("offset", "0")
        params.add("limit", "10")

        given:
        this.heroService.getHeroes("", "", false, 0, 10) >> {
            throw new NotFoundHero("Nenhum heroi foi encontrado na base de dados")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nenhum heroi foi encontrado na base de dados'}"))
    }

    def "Get heroes should return 400 when default limit is exceeded"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "")
        params.add("difficult", "")
        params.add("recommend", "false")
        params.add("offset", "0")
        params.add("limit", "30")

        given:
        this.heroService.getHeroes("", "", false, 0, 30) >> {
            throw new LimitExceeded("Você esta colocando um limite acima do máximo permitido por padrao (default: 20)")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'Você esta colocando um limite acima do máximo " +
                "permitido por padrao (default: 20)'}"))
    }

    def "Get heroes should return 200 when limit is valid"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "")
        params.add("difficult", "")
        params.add("recommend", "false")
        params.add("offset", "0")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isOk())
    }

    def "Get heroes should return 400 when offset exceed heroes return quantity"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "")
        params.add("difficult", "")
        params.add("recommend", "false")
        params.add("offset", "30")
        params.add("limit", "10")

        given:
        this.heroService.getHeroes("", "", false, 30, 10) >> {
            throw new OffsetExceeded("Offset colocado está acima da quantidade de herois retornados")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'Offset colocado está acima da quantidade de " +
                "herois retornados'}"))
    }

    def "Get heroes should return 200 when offset is valid"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "")
        params.add("difficult", "")
        params.add("recommend", "false")
        params.add("offset", "20")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isOk())
    }

    def "Get a hero recommend should return a status code 200 when param is true and lane/difficult are correctly"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "safe")
        params.add("difficult", "easy")
        params.add("recommend", "true")
        params.add("offset", "20")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isOk())
    }

    def "Get a hero recommend should return a status code 404 when not found a hero in the informed lane "() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "safe")
        params.add("difficult", "easy")
        params.add("recommend", "true")
        params.add("offset", "20")
        params.add("limit", "10")

        this.heroService.getHeroes("safe", "easy", true, 20, 10) >> {
            throw new NotFoundHero("Nao foi encontrado nenhum heroi nesta lane")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nao foi encontrado nenhum heroi nesta lane'}"))
    }

    def "Get a hero recommend should return 400 when informed lane is invalid"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "low")
        params.add("difficult", "easy")
        params.add("recommend", "true")
        params.add("offset", "20")
        params.add("limit", "10")

        this.heroService.getHeroes("low", "easy", true, 20, 10) >> {
            throw new InvalidHeroLane("A lane informada do heroi nao existe. As lanes existentes são: safe, off e mid")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

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
        params.add("recommend", "true")
        params.add("offset", "20")
        params.add("limit", "10")
        this.heroService.getHeroes("off", "overkill", true, 20, 10) >> {
            throw new InvalidHeroDifficult("A dificuldade informada do heroi nao existe. As dificuldades existentes" +
                    " são: easy, medium e hard")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'A dificuldade informada do heroi nao existe." +
                " As dificuldades existentes são: easy, medium e hard'}"))
    }

    def "Get a hero recommend should return a status code 404 when not found a hero in the informed difficult "() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("lane", "safe")
        params.add("difficult", "easy")
        params.add("recommend", "true")
        params.add("offset", "20")
        params.add("limit", "10")

        this.heroService.getHeroes("safe", "easy", true, 20, 10) >> {
            throw new NotFoundHero("Nao foi encontrado nenhum heroi nesta dificuldade")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes").params(params))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nao foi encontrado nenhum heroi nesta " +
                "dificuldade'}"))
    }

    def "Get a hero skins should return 200 when path param is valid"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap()
        params.add("offset", "0")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1).params(params))

        then:
        response.andExpect(status().isOk())
    }

    def "Get a hero skins should return 404 when not found a hero with the informed id"() {
        given:
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap()
        Integer id = 1
        Integer offset = 0
        Integer limit = 10
        params.add("offset", offset.toString())
        params.add("limit", limit.toString())
        this.heroService.getHeroSkins(id, offset, limit) >> {
            throw new NotFoundHero("Nao foi encontrado nenhum heroi com o id informado")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1).params(params))

        then:
        response.andExpect(status().isNotFound())
        response.andExpect(content().json("{'errorMessage':'Nao foi encontrado nenhum heroi com o id " +
                "informado'}"))
    }

    def "Get hero skins should return 200 when offset is valid"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("offset", "20")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1).params(params))

        then:
        response.andExpect(status().isOk())
    }

    def "Get hero skins should return 400 when offset exceed heroes return quantity"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("offset", "30")
        params.add("limit", "10")

        given:
        this.heroService.getHeroSkins(1, 30, 10) >> {
            throw new OffsetExceeded("Offset colocado está acima da quantidade de skins retornadas")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1).params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'Offset colocado está acima da quantidade de " +
                "skins retornadas'}"))
    }

    def "Get hero skins should return 400 when default limit is exceeded"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("offset", "0")
        params.add("limit", "30")

        given:
        this.heroService.getHeroSkins(1, 0, 30) >> {
            throw new LimitExceeded("Você esta colocando um limite acima do máximo permitido por padrao (default: 20)")
        }

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1).params(params))

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(content().json("{'errorMessage':'Você esta colocando um limite acima do máximo " +
                "permitido por padrao (default: 20)'}"))
    }

    def "Get hero skins should return 200 when limit is valid"() {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>()
        params.add("offset", "0")
        params.add("limit", "10")

        when:
        ResultActions response = mockMvc.perform(get("/v1/heroes/{id}/skins", 1).params(params))

        then:
        response.andExpect(status().isOk())
    }


    //TODO Seguir nos testes finais da controller -
}
