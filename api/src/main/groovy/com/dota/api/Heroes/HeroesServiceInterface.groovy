package com.dota.api.Heroes

import com.dota.api.Skills.Skill
import com.dota.api.Skins.Skin

interface HeroesServiceInterface {
    List<Hero> getHeroes()

    Hero getHeroRecommendation(String lane, String difficult)

    List<Skin> getHeroSkins(Integer id)

    List<Skill> getHeroSkills(Integer id)

    void createHero()

    void editHero(Integer id)

    void deleteHero(Integer id)
}