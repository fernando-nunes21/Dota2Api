package com.dota.api.Heroes

interface HeroServiceInterface {
    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit)

    void createHero()

    void editHero(Integer id)

    void deleteHero(Integer id)
}