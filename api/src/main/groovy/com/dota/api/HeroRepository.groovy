package com.dota.api

import com.dota.api.Heroes.Hero
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class HeroRepository {

    private JdbcTemplate jdbcTemplate

    HeroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    void insert(Hero hero){
        String sqlHero = "INSERT INTO heroes (name, lane, difficult, skills, skins) VALUES (?, ?, ?, ?, ?)"
        jdbcTemplate.update(sqlHero, hero.name, hero.lane, hero.difficult, hero.skills, hero.skins)
    }
}
