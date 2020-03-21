package org.wirvsvirushackathon.answers

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.wirvsvirushackathon.questionnaire.Questionnaire

@Repository
class AnswerRepository constructor(
        val jdbcTemplate: JdbcTemplate
        ) {

    fun insertAnswer(answer: AnswerInputDTO) = jdbcTemplate.update(
            "INSERT INTO ANSWER (question_id, people_id, content) VALUES (?,?,?);",
             answer.questionId, answer.peopleId, answer.content
    )
}
