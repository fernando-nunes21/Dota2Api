package com.dota.api.Heroes

import org.springframework.stereotype.Service

@Service
class HeroService implements HeroServiceInterface {

    List<Hero> getHeroes(String lane, String difficult, Boolean recommend, Integer offset, Integer limit) {
        return null
    }

    void createHero() {

    }

    void editHero(Integer id) {

    }

    void deleteHero(Integer id) {

    }

}
