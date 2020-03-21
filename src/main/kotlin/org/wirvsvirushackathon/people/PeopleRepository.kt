package org.wirvsvirushackathon.people

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet

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

    fun getPeopleByToken(token: String): People? {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM people WHERE token = :token",
                    MapSqlParameterSource().addValue("token", token),
                    ({ resultSet, i -> peopleRowMapper(resultSet) })
            )
        } catch (e: EmptyResultDataAccessException) {
            return null
        }
    }

    fun tokenAlreadyExists(token: String) =
            jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM people WHERE token = :token",
                    MapSqlParameterSource().addValue("token", token),
                    Int::class.java
            ) == 1

    private fun peopleRowMapper(resultSet: ResultSet) =
            People(
                    resultSet.getLong("id"),
                    resultSet.getString("token")
            )
}
