package org.wirvsvirushackathon.answers

import java.time.ZonedDateTime


data class Answer(
        val questionId: Long,
        val content: String,
        val timestamp: ZonedDateTime
)

data class AnswerInputDTO(
        val questionId: Long,
        val peopleId: Long,
        val content: String
)
