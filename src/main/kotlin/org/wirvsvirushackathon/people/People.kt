package org.wirvsvirushackathon.people

import org.wirvsvirushackathon.answers.AnswerWithQuestion
import java.time.ZonedDateTime

data class People(
        val id: Long,
        val token: String,
        val status: PeopleStatus,
        val creationTimestamp: ZonedDateTime,
        val latitude: Double,
        val longitude: Double,
        val initialQuestionnaireAnswers: List<AnswerWithQuestion>
)

data class TokenDto(
        val token: String
)

enum class PeopleStatus {
    CREATED,
    ACTIVE,
    INACTIVE
}
