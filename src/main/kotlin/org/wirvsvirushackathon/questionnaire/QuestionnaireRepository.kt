package org.wirvsvirushackathon.questionnaire

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.wirvsvirushackathon.text.TextRepository
import java.sql.ResultSet

@Repository
class QuestionnaireRepository constructor(
        val jdbcTemplate: JdbcTemplate,
        val textRepository: TextRepository) {

    fun findById(id: Long): Questionnaire? = jdbcTemplate.queryForObject(
            "SELECT * FROM questionnaire WHERE id = ?",
            RowMapper { rs: ResultSet, _: Int ->
                Questionnaire(rs.getLong("id"),
                        textRepository.findById(rs.getLong("title")),
                        findQuestionsByQuestionnaireId(1)
                )
            }, id
    )

    fun findQuestionsByQuestionnaireId(id: Long): List<Question> = jdbcTemplate.query(
            "SELECT * FROM question WHERE questionnaire_id = ?",
            RowMapper { rs: ResultSet, _: Int ->
                Question(rs.getLong("id"),
                        QuestionType.valueOf(rs.getString("type")),
                        textRepository.findById(rs.getLong("question_text"))
                )
            }, id
    )


}
