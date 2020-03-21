package org.wirvsvirushackathon.infectedpeople

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/infected-people")
class InfectedPeopleController (val infectedPeopleService: InfectedPeopleService) {

    @PostMapping
    fun register(): InfectedPeople = infectedPeopleService.createNewInfectedPeople()
}