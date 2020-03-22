package org.wirvsvirushackathon.people

import org.wirvsvirushackathon.answers.Answer
import java.time.ZonedDateTime

data class People(
        val id: Long,
        val token: String,
        val status: PeopleStatus,
        val creationTimestamp: ZonedDateTime,
        val initialQuestionnaireAnswers: List<Answer>
)

data class TokenDto(
        val token: String
)

enum class PeopleStatus {
    CREATED,
    ACTIVE,
    INACTIVE
}