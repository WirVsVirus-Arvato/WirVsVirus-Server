package org.wirvsvirushackathon.answers

import java.time.ZonedDateTime


data class Answer(
        val questionId: Long,
        val content: String,
        val timestamp: ZonedDateTime
)

data class AnswerListDto(
        val token: String,
        val answers: List<Answer>
)

data class AnswerInputDTO(
        val questionId: Long,
        val peopleId: Long,
        val content: String
)