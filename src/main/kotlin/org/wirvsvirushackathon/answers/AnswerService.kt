package org.wirvsvirushackathon.answers

import org.springframework.stereotype.Service

@Service
class AnswerService(val answerRepository: AnswerRepository) {

    fun insertAnswer(answer: AnswerInputDTO) = answerRepository.insertAnswer(answer)

    fun getAnswersByPeopleToken(token: String) =
            answerRepository.getAnswersByPeopleToken(token)

    fun getListOfAnswersFromMultipleTokens(tokens: List<String>): ArrayList<AnswerListDto> {
        val answers: ArrayList<AnswerListDto> = arrayListOf()

        tokens.forEach {
            answers.add(AnswerListDto(it, answerRepository.getInitialQuestionnaireAnswersByPeopleToken(it)))
        }

        return answers
    }
}
