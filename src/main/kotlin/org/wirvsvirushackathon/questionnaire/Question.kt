package org.wirvsvirushackathon.questionnaire


data class Question(
        override val id: Long,
        override val type: QuestionType,
        override val title: String?
) : IQuestion

data class OptionQuestion(
        override val id: Long,
        override val type: QuestionType,
        override val title: String?,
        val choices: List<Choice>
) : IQuestion

interface IQuestion {
    val id: Long
    val type: QuestionType
    val title: String?
}

enum class QuestionType {
    NUMERIC10,
    MULTIPLECHOICE,
    SINGLECHOICE
}
