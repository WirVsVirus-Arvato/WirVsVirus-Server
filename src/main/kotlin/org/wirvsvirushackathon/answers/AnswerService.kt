package org.wirvsvirushackathon.answers

import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class AnswerService(val answerRepository: AnswerRepository) {

    fun insertAnswer(answer: List<AnswerInputDTO>) = answerRepository.insertAnswer(answer)

    fun getAnswersByPeopleToken(token: String) =
            answerRepository.getAnswersByPeopleToken(token)

    fun getListOfAnswersFromMultipleTokens(tokens: List<String>): ArrayList<AnswerListDto> {
        val answers: ArrayList<AnswerListDto> = arrayListOf()

        tokens.forEach {
            val peopleAnswers = answerRepository.getInitialQuestionnaireAnswersByPeopleToken(it)
            val answerMap = mutableMapOf<String, List<AnswerWithQuestion>>()
            peopleAnswers.forEach { it ->
                val formattedDate = it.timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val listForTime: ArrayList<AnswerWithQuestion> = (answerMap.get(formattedDate)
                        ?: mutableListOf<AnswerWithQuestion>()) as ArrayList<AnswerWithQuestion>
                listForTime.add(AnswerWithQuestion(it.question, it.answer, it.timestamp))

                answerMap.set(
                        formattedDate,
                        listForTime
                )
            }
            answers.add(AnswerListDto(it, answerMap))
        }

        return answers
    }
}
