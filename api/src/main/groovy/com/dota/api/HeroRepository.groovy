package com.dota.api

import com.dota.api.Heroes.Hero
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

import java.sql.Array
import java.sql.ResultSet
import java.sql.Types

@Service
class HeroRepository {

    private JdbcTemplate jdbcTemplate
    private final Integer VALUE_ONE_TO_SUM_ID_HERO = 1

    HeroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    void insert(Hero hero) {
        String sql = "INSERT INTO heroes (id, name, lane, difficult, skills, skins) VALUES (?, ?, ?, ?, ?, ?)"
        Object[] params = new Object[]{
                getLastIdHero() + VALUE_ONE_TO_SUM_ID_HERO,
                hero.name,
                hero.lane.toLowerCase(),
                hero.difficult.toLowerCase(),
                createSqlArray(hero.skills),
                createSqlArray(hero.skins)}
        int[] types = new int[] {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.ARRAY, Types.ARRAY}
        jdbcTemplate.update(sql,
                params,
                types
        )
    }

    private Array createSqlArray(List<String> list){
        return jdbcTemplate.getDataSource().getConnection().createArrayOf("text", list.toArray())
    }

    private Integer getLastIdHero(){
        String sql = "SELECT count(*) FROM heroes"
        int[] types = new int[]{}
        return jdbcTemplate.queryForObject(sql, new Object[]{}, types , Integer)
    }


}
