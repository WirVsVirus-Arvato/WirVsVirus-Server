package org.wirvsvirushackathon.questionnaire

data class Questionnaire (
    val id: Long,
    val title: String?,
    val questions: List<Question>
)
