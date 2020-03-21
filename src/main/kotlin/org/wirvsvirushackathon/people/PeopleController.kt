package org.wirvsvirushackathon.people

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/people")
class PeopleController(val peopleService: PeopleService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(): People =
            peopleService.createNewInfectedPeople()

    @GetMapping
    fun getOnePeople(@RequestHeader("token") token: String) =
            peopleService.getPeopleByToken(token) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}