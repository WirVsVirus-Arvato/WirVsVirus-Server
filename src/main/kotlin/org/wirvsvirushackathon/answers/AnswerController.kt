package org.wirvsvirushackathon.answers

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/answer")
class AnswerController(val answerService: AnswerService) {

    @PostMapping
    fun create(answer: AnswerInputDTO) = answerService.insertAnswer(answer)

    @GetMapping
    fun getAnswers(@RequestParam token: String) = answerService.getAnswersByPeopleToken(token)

    @GetMapping("/collect")
    fun getAnswersFromMultipleTokens(@RequestParam(name = "from") tokens: String): ArrayList<AnswerListDto> {
        val tokenArray = tokens.split(',')
        return answerService.getListOfAnswersFromMultipleTokens(tokenArray)
    }

}
