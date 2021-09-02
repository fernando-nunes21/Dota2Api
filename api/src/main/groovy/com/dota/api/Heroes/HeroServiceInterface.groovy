package com.dota.api.Heroes

import com.dota.api.Skills.Skill
import com.dota.api.Skins.Skin

interface HeroServiceInterface {
    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit)

    List<Skin> getHeroSkins(Integer id, Integer offset, Integer limit)

    List<Skill> getHeroSkills(Integer id, Integer offset, Integer limit)

    void createHero()

    void editHero(Integer id)

    void deleteHero(Integer id)
}