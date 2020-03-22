package org.wirvsvirushackathon.people

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["/people"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PeopleController(val peopleService: PeopleService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register() = peopleService.createNewInfectedPeople()

    @GetMapping("/{token}")
    fun getOnePeople(@PathVariable token: String) =
            peopleService.getPeopleByToken(token)

    @PostMapping("/{token}/activate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun activate(@PathVariable token: String) =
            peopleService.activateByToken(token)

    @PostMapping("/{token}/deactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deactivate(@PathVariable token: String) =
            peopleService.deactivateByToken(token)
}