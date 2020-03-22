package org.wirvsvirushackathon.answers

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.ZoneId

@Repository
class AnswerRepository constructor(
        val jdbcTemplate: JdbcTemplate
        ) {

    fun insertAnswer(answer: AnswerInputDTO) = jdbcTemplate.update(
            "INSERT INTO ANSWER (question_id, people_id, content) VALUES (?,?,?);",
             answer.questionId, answer.peopleId, answer.content
    )

    fun getAnswersByPeopleToken(token: String) =
        jdbcTemplate.query(
                "SELECT question_id, content, timestamp " +
                        "FROM answer a " +
                        "INNER JOIN people p on a.people_id = p.id " +
                        "where p.token = ?",

                RowMapper { rs: ResultSet, _ ->
                    Answer(
                            questionId = rs.getLong("question_id"),
                            content = rs.getString("content"),
                            timestamp = rs.getTimestamp("timestamp").toInstant().atZone(ZoneId.systemDefault())
                    )
                },
                token).toList()
}
