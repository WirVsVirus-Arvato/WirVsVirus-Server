package org.wirvsvirushackathon.people

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.awt.Point
import java.sql.ResultSet
import java.time.ZoneId

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
        return getPeopleById(id)
    }

    fun getPeopleByToken(token: String): People {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM people WHERE token = :token",
                    MapSqlParameterSource().addValue("token", token))
                    { resultSet, _ -> peopleRowMapper(resultSet) } ?: throw PeopleNotExistsException(token)
        } catch (e: EmptyResultDataAccessException) {
            throw PeopleNotExistsException(token)
        }
    }

    fun getPeopleById(id: Long): People {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM people WHERE id = :id",
                    MapSqlParameterSource().addValue("id", id)
            ) { resultSet, _ -> peopleRowMapper(resultSet) } ?: throw PeopleNotFoundException(id)
        } catch (e: EmptyResultDataAccessException) {
            throw PeopleNotFoundException(id)
        }
    }

    fun tokenAlreadyExists(token: String) =
            jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM people WHERE token = :token",
                    MapSqlParameterSource().addValue("token", token),
                    Int::class.java
            ) == 1

    fun setStatus(token: String, status: PeopleStatus) =
            jdbcTemplate.update(
                    "UPDATE people SET  status = :status WHERE token = :token",
                    MapSqlParameterSource()
                            .addValue("token", token)
                            .addValue("status", status.name)
            )


    private fun peopleRowMapper(resultSet: ResultSet) =
            People(
                    id= resultSet.getLong("id"),
                    token = resultSet.getString("token"),
                    status = PeopleStatus.valueOf(resultSet.getString("status")),
                    creationTimestamp = resultSet.getTimestamp("creation_timestamp").toInstant().atZone(ZoneId.systemDefault())
//                    location = resultSet.getObject("location", Point::class.java)
            )

}
