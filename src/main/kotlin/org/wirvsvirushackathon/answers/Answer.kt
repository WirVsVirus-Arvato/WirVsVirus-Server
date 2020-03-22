package org.wirvsvirushackathon.answers

import java.time.ZonedDateTime


data class Answer(
        val questionId: Long,
        val content: String,
        val timestamp: ZonedDateTime
)

data class AnswerWithQuestion(
        val question: String,
        val answer: String,
        val timestamp: ZonedDateTime
)

data class AnswerListDto(
        val token: String,
        val answers: List<AnswerWithQuestion>
)

data class AnswerInputDTO(
        val questionId: Long,
        val peopleId: Long,
        val content: String
)