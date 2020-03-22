package org.wirvsvirushackathon.questionnaire

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/questionnaire")
class QuestionnaireController(val questionnaireService: QuestionnaireService) {

    @GetMapping
    fun getQuestionnaire(@RequestParam(value = "id", defaultValue = "1") id: Long) =
            questionnaireService.findQuestionnaireById(id)
}
