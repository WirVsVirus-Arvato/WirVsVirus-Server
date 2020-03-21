package org.wirvsvirushackathon.answers

import org.springframework.stereotype.Service
import org.wirvsvirushackathon.questionnaire.QuestionnaireRepository

@Service
class AnswerService (val answerRepository: AnswerRepository) {

    fun inserAnswer(answer: AnswerInputDTO) =  answerRepository.insertAnswer(answer)
}
