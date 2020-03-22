package org.wirvsvirushackathon.questionnaire

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.wirvsvirushackathon.text.TextRepository
import java.io.InvalidObjectException
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
                        findQuestionsByQuestionaireId(id)
                        )
            }, id
    )

    fun findQuestionsByQuestionaireId(id: Long): List<IQuestion> = jdbcTemplate.query(
    "SELECT question.id,question.type,text_lang.text FROM question " +
            "LEFT JOIN text_lang ON(question_text = text_lang.id) " +
            "WHERE questionnaire_id = ?",
    RowMapper { rs: ResultSet, _: Int ->
        val question_type = QuestionType.valueOf(rs.getString("type"))
        if(question_type == QuestionType.NUMERIC10){
            Question(rs.getLong("id"),
                    question_type,
                    rs.getString("text")
            )
        }else if(question_type == QuestionType.MULTIPLECHOICE){
            MultipleChoiceQuestion(rs.getLong("id"),
                    question_type,
                    rs.getString("text"),
                    findChoices(rs.getLong("id"))
            )
        }else{
            throw InvalidObjectException("Question type is not defined")
        }

    }, id
    )

    fun findChoices(questionId: Long): List<Choice> = jdbcTemplate.query(
        "SELECT mca.id, text_lang.text FROM multiple_choice_answer mca " +
                "LEFT JOIN text_lang ON(mca.answer = text_lang.id)  " +
                "WHERE mca.question_id = ?",
        RowMapper { rs: ResultSet, _: Int ->
            Choice(rs.getLong("id"),
                    rs.getString("text")
            )
        },questionId
    )

}
