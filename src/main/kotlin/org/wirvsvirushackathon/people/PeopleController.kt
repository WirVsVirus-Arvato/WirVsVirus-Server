package org.wirvsvirushackathon.people

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.wirvsvirushackathon.USER_TOKEN

@RestController
@RequestMapping(
        value = ["/people"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PeopleController(val peopleService: PeopleService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register() = peopleService.createNewInfectedPeople().token

    @GetMapping
    fun getOnePeople(@RequestParam(USER_TOKEN) token: String) =
            peopleService.getPeopleByToken(token)

}