package com.dota.api.Heroes

import com.dota.api.Skills.Skill
import com.dota.api.Skins.Skin

interface HeroServiceInterface {
    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit)

    List<Skin> getHeroSkins(Integer id)

    List<Skill> getHeroSkills(Integer id)

    void createHero()

    void editHero(Integer id)

    void deleteHero(Integer id)
}