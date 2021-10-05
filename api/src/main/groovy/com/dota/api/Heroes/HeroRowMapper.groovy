package com.dota.api.Heroes

import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class HeroRowMapper implements RowMapper<Hero> {
    Hero mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        Hero hero = new Hero()
        hero.setId(resultSet.getInt("id"))
        hero.setName(resultSet.getString("name"))
        hero.setLane(resultSet.getString("lane"))
        hero.setDifficult(resultSet.getString("difficult"))
        String skills = resultSet.getArray("skills")
        String skins = resultSet.getArray("skins")
        hero.setSkills(convertStringToList(skills))
        hero.setSkins(convertStringToList(skins))
        return hero
    }

    private List<String> convertStringToList (String string){
        String result = string.replace('{','')
                .replace('}','')
        return new ArrayList<String>(Arrays.asList(result.split(",")))
    }
}

