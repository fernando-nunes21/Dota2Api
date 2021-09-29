package com.dota.api.Heroes

import com.dota.api.Errors.HeroInvalidFields
import com.dota.api.HeroRepository
import org.springframework.stereotype.Service

@Service
class HeroService implements HeroServiceInterface {

    private HeroRepository heroRepository

    HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository
    }

    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit) {
        if(recommend){

        } else{

        }
        return null
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
        fieldLaneValidation(hero.lane.toLowerCase())
        fieldDifficultValidation(hero.difficult.toLowerCase())
        fieldSkillsValidation(hero.skills)
        fieldSkinsValidation(hero.skins)
    }

    private void fieldNameValidation(String name) {
        if (isFieldInvalid(name)) {
            throw new HeroInvalidFields("O campo 'name' está vazio ou é nulo")
        }
    }

    private void fieldLaneValidation(String lane) {
        if (isFieldInvalid(lane)) {
            throw new HeroInvalidFields("O campo 'lane' está vazio ou é nulo")
        }
        if (lane != "safe" && lane != "mid" && lane != "off") {
            throw new HeroInvalidFields("O campo 'lane' nao esta setado corretamente. As lanes disponiveis sao: " +
                    "'Safe, Mid, Off'")
        }
    }

    private void fieldDifficultValidation(String difficult) {
        if (isFieldInvalid(difficult)) {
            throw new HeroInvalidFields("O campo 'difficult' está vazio ou é nulo")
        }
        if (difficult != "easy" && difficult != "medium" && difficult != "hard") {
            throw new HeroInvalidFields("O campo 'difficult' nao esta setado corretamente. As dificuldades " +
                    "disponiveis sao: 'Easy, Medium, Hard'")
        }
    }

    private void fieldSkillsValidation(List<String> skills) {
        if (isFieldInvalid(skills)) {
            throw new HeroInvalidFields("O campo 'skills' é nulo ou nao contem nenhuma skill. É obrigatório o heroi " +
                    "ter no mínimo 1 skill")
        }
    }

    private void fieldSkinsValidation(List<String> skins) {
        if (isFieldInvalid(skins)) {
            throw new HeroInvalidFields("O campo 'skins' é nulo ou nao contem nenhuma skin. É obrigatorio o heroi ter" +
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

}
