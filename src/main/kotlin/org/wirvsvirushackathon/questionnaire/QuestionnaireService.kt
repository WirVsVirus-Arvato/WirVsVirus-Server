package org.wirvsvirushackathon.questionnaire

import org.springframework.stereotype.Service

@Service
class QuestionnaireService (val questionnaireRepository: QuestionnaireRepository) {

    fun findQuestionaireById(id: Long) = questionnaireRepository.findById(id)
}
