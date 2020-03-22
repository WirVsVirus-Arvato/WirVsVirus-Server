package org.wirvsvirushackathon.people.geocoding

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import org.wirvsvirushackathon.answers.AnswerRepository
import java.sql.ResultSet
import java.time.ZoneId

@Repository
class GeoRepository(val jdbcTemplate: NamedParameterJdbcTemplate, val answerRepository: AnswerRepository) {

    fun saveGeo(geoInputDTO: GeoInputDTO, token: String) {
        val key = GeneratedKeyHolder()
        jdbcTemplate.update(
                "INSERT INTO geo (longitude, latitude, people_id) VALUES (:longitude, :latitude, (Select id from people where token = :token))",
                MapSqlParameterSource().addValue("longitude", geoInputDTO.longitude)
                .addValue("latitude", geoInputDTO.latitude)
                .addValue("token", token),
                key
        )
    }



}
