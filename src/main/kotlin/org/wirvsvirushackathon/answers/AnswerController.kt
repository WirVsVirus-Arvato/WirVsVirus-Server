package org.wirvsvirushackathon.answers

import org.springframework.web.bind.annotation.*
import org.wirvsvirushackathon.questionnaire.QuestionnaireService

@RestController
@RequestMapping("/anwswer")
class AnswerController (val answerService: AnswerService) {

    @PostMapping
    fun hello(answer: AnswerInputDTO) = answerService.inserAnswer(answer)

    @GetMapping
    fun getAnswers(@RequestParam token: String) = answerService.getAnswersByPeopleToken(token)

}
