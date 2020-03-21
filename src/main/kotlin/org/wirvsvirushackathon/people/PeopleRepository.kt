package org.wirvsvirushackathon.people

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class PeopleRepository(val jdbcTemplate: NamedParameterJdbcTemplate) {

    fun create(token: String): People {
        val key = GeneratedKeyHolder()
        jdbcTemplate.update(
                "INSERT INTO people (token) VALUES (:token)",
                MapSqlParameterSource().addValue("token", token),
                key
        )

        val id: Long = key.keys?.get("id") as Long
        return People(id, token)
    }

    fun tokenAlreadyExists(token: String): Boolean {
        val result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM people WHERE token = :token",
                MapSqlParameterSource().addValue("token", token),
                Int::class.java
        )

        return result == 1
    }
}
