package org.wirvsvirushackathon.text

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class TextRepository constructor(val jdbcTemplate: JdbcTemplate) {

    fun findById(id: Long): String? = jdbcTemplate.queryForObject(
            "SELECT * FROM text_lang WHERE id = ? AND language_id = 1",
            RowMapper { rs: ResultSet, _: Int ->
                rs.getString("text")
            }, id
    )
}
