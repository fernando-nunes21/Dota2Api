package com.dota.api

import com.dota.api.Errors.NotFoundHero
import com.dota.api.Heroes.Hero
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

import java.sql.Array

@Service
class HeroRepository {

    private JdbcTemplate jdbcTemplate

    HeroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }


    void insert(Hero hero) {
        String sql = "INSERT INTO heroes (name, lane, difficult, skills, skins) VALUES (?, ?, ?, ?, ?)"
        Object[] params = new Object[]{
                hero.name,
                hero.lane.toLowerCase(),
                hero.difficult.toLowerCase(),
                createSqlArray(hero.skills),
                createSqlArray(hero.skins)}
        jdbcTemplate.update(sql,
                params
        )
    }

    void edit(Integer id) {

    }

    void delete(Integer id) {
        String sql = "DELETE FROM heroes WHERE id = ?"
        Object[] params = new Object[]{id}
        int result = jdbcTemplate.update(sql, params)
        if (result == 0) {
            throw new NotFoundHero("O heroi com id informado não existe. Por favor, informe um id válido.")
        }
    }

    private Array createSqlArray(List<String> list) {
        return jdbcTemplate.getDataSource().getConnection().createArrayOf("text", list.toArray())
    }
}
