package com.dota.api.Heroes

interface HeroServiceInterface {
    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit)

    void createHero(Hero hero)

    void editHero(Integer id, Hero hero)

    void deleteHero(Integer id)
}