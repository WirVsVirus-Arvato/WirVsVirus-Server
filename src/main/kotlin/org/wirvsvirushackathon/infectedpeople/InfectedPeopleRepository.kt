package org.wirvsvirushackathon.infectedpeople

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class InfectedPeopleRepository(val jdbcTemplate: NamedParameterJdbcTemplate) {

    fun create(token: String): InfectedPeople {
        val key = GeneratedKeyHolder()
        jdbcTemplate.update(
                "INSERT INTO infected_people (token) VALUES (:token)",
                MapSqlParameterSource().addValue("token", token),
                key
        )

        val id: Long = key.keys?.get("id") as Long
        return InfectedPeople(id, token)
    }

    fun tokenAlreadyExists(token: String): Boolean {
        val result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM infected_people WHERE token = :token",
                MapSqlParameterSource().addValue("token", token),
                Int::class.java
        )

        return result == 1
    }
}
