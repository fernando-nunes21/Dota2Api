package com.dota.api.Heroes

import com.dota.api.Skills.Skill
import com.dota.api.Skins.Skin

class HeroService implements HeroServiceInterface {

    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit) {
        return null
    }

    List<Skin> getHeroSkins(Integer id) {
        return null
    }

    List<Skill> getHeroSkills(Integer id) {
        return null
    }

    void createHero() {

    }

    void editHero(Integer id) {

    }

    void deleteHero(Integer id) {

    }

}
