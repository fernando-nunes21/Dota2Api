package com.dota.api.Heroes

import com.dota.api.Errors.HeroInvalidField
import com.dota.api.Errors.LimitExceeded
import com.dota.api.Repository.HeroRepository
import org.springframework.stereotype.Service

@Service
class HeroService implements HeroServiceInterface {

    private HeroRepository heroRepository
    private final Integer DEFAULT_LIMIT = 30

    HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository
    }

    List<Hero> getHeroes(String lane, String difficult, Integer offset, Integer limit) {
        String filterLane = filterFieldValidation(lane)
        String filterDifficult = filterFieldValidation(difficult)
        if(isLimitExceeded(limit)) {
            throw new LimitExceeded("O limite informado ultrapassou o limite máximo permitido (DEFAULT = 30)")
        }
        return this.heroRepository.selectHeroes(filterLane, filterDifficult, offset, limit)
    }

    void createHero(Hero hero) {
        fieldsValidations(hero)
        this.heroRepository.insert(hero)
    }

    void editHero(Integer id, Hero hero) {
        fieldsValidations(hero)
        this.heroRepository.edit(id, hero)
    }

    void deleteHero(Integer id) {
        this.heroRepository.delete(id)
    }

    private void fieldsValidations(Hero hero){
        fieldNameValidation(hero.name)
        fieldLaneValidation(hero.lane)
        fieldDifficultValidation(hero.difficult)
        fieldSkillsValidation(hero.skills)
        fieldSkinsValidation(hero.skins)
    }

    private void fieldNameValidation(String name) {
        if (isFieldInvalid(name)) {
            throw new HeroInvalidField("O campo 'name' está vazio ou é nulo")
        }
    }

    private void fieldLaneValidation(String lane) {
        if (isFieldInvalid(lane)) {
            throw new HeroInvalidField("O campo 'lane' está vazio ou é nulo")
        }
        if (lane.toLowerCase() != "safe" && lane.toLowerCase() != "mid" && lane.toLowerCase() != "off") {
            throw new HeroInvalidField("O campo 'lane' nao esta setado corretamente. As lanes disponiveis sao: " +
                    "'Safe, Mid, Off'")
        }
    }

    private void fieldDifficultValidation(String difficult) {
        if (isFieldInvalid(difficult)) {
            throw new HeroInvalidField("O campo 'difficult' está vazio ou é nulo")
        }
        if (difficult.toLowerCase()  != "easy" &&
                difficult.toLowerCase()  != "medium" &&
                difficult.toLowerCase()  != "hard") {
            throw new HeroInvalidField("O campo 'difficult' nao esta setado corretamente. As dificuldades " +
                    "disponiveis sao: 'Easy, Medium, Hard'")
        }
    }

    private void fieldSkillsValidation(List<String> skills) {
        if (isFieldInvalid(skills)) {
            throw new HeroInvalidField("O campo 'skills' é nulo ou nao contem nenhuma skill. É obrigatório o heroi " +
                    "ter no mínimo 1 skill")
        }
    }

    private void fieldSkinsValidation(List<String> skins) {
        if (isFieldInvalid(skins)) {
            throw new HeroInvalidField("O campo 'skins' é nulo ou nao contem nenhuma skin. É obrigatorio o heroi ter" +
                    "no mínimo 1 skin")
        }
    }

    private boolean isFieldInvalid(String field) {
        if (field == null || field == "") {
            return true
        }
        return false
    }

    private boolean isFieldInvalid(List<String> field) {
        if (field == null || field.size() < 1) {
            return true
        }
        return false
    }

    private String filterFieldValidation(String field){
        if(isFieldInvalid(field)){
            return "%"
        } else{
            return field
        }
    }

    private boolean isLimitExceeded(Integer limit){
        return limit > DEFAULT_LIMIT
    }
}
