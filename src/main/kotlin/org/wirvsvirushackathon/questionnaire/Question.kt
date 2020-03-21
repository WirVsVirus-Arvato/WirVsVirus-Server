package org.wirvsvirushackathon.questionnaire

data class Question(
        val id: Long,
        val type: QuestionType,
        val title: String?
)

enum class QuestionType {
    NUMERIC10
}
