package org.wirvsvirushackathon.questionnaire

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.wirvsvirushackathon.questionnaire.QuestionnaireService

@RestController
@RequestMapping("/questionnaire")
class QuestionnaireController (val questionnaireService: QuestionnaireService) {

    @GetMapping
    fun hello(@RequestParam(value = "id", defaultValue = "1") id: Long) = questionnaireService.findQuestionaireById(id)
}
