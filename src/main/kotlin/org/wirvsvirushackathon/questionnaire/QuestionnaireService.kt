package org.wirvsvirushackathon.questionnaire

import org.springframework.stereotype.Service
import org.wirvsvirushackathon.answers.AnswerRepository

@Service
class QuestionnaireService (val questionnaireRepository: QuestionnaireRepository) {

    fun findQuestionnaireById(id: Long) = questionnaireRepository.findById(id)
}
