package org.wirvsvirushackathon.hello

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class HelloRepository constructor(val jdbcTemplate: JdbcTemplate) {

    fun findById(id: Long): Hello? = jdbcTemplate.queryForObject(
            "SELECT * FROM person WHERE id = ?",
            RowMapper { rs: ResultSet, _: Int ->
                Hello(rs.getLong("ID").toString())
            }, id
    )
}
