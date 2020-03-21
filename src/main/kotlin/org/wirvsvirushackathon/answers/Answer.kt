package org.wirvsvirushackathon.answers

import java.time.OffsetDateTime

data class Answer(
        val id: Long,
        val questionId: Long,
        val peopleId: Long,
        val content: String,
        val timestamp: OffsetDateTime
)

data class AnswerInputDTO(
        val questionId: Long,
        val peopleId: Long,
        val content: String
)
