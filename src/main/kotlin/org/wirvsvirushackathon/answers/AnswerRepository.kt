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

    fun insertAnswer(answers: List<AnswerInputDTO>) {
        for (answer in answers) {
            jdbcTemplate.update(
                    "INSERT INTO ANSWER (question_id, people_id, content) VALUES (?,(Select id from people where token = ?),?);",
                    answer.questionId, answer.token, answer.content
            )
        }
    }

    fun getAnswersByPeopleToken(token: String) =
            jdbcTemplate.query(
                    "SELECT question_id, content, timestamp " +
                            "FROM answer a " +
                            "INNER JOIN people p on a.people_id = p.id " +
                            "WHERE p.token = ?",

                    RowMapper { rs: ResultSet, _ ->
                        Answer(
                                questionId = rs.getLong("question_id"),
                                content = rs.getString("content"),
                                timestamp = rs.getTimestamp("timestamp").toInstant().atZone(ZoneId.systemDefault())
                        )
                    },
                    token).toList()

    fun getInitialQuestionnaireAnswersByPeopleToken(token: String) =
            jdbcTemplate.query(
                    """
                        SELECT t.text, string_agg(t2.text, ',') as mca_as_text, a.timestamp
                        FROM answer AS a
                                 INNER JOIN question q on a.question_id = q.id
                                 INNER JOIN text_lang t on q.question_text = t.id
                                 INNER JOIN questionnaire q2 on q.questionnaire_id = q2.id
                                 INNER JOIN multiple_choice_answer mca
                                            on mca.answer = ANY (regexp_split_to_array(a.multiple_choice_answer_ids, ',')::bigint[])
                                 INNER JOIN text_lang t2 on mca.answer = t2.id
                                 INNER JOIN people p on a.people_id = p.id
                        WHERE p.token = ?
                          AND q2.id = 1
                        GROUP BY t.text, a.multiple_choice_answer_ids, a.timestamp;
                    """,
                    RowMapper { rs: ResultSet, _ ->
                        AnswerWithQuestion(
                                rs.getString("text"),
                                rs.getString("mca_as_text"),
                                rs.getTimestamp("timestamp").toInstant().atZone(ZoneId.systemDefault())
                        )
                    },
                    token).toList()
}
