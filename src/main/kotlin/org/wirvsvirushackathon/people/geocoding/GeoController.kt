package org.wirvsvirushackathon.people.geocoding

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.wirvsvirushackathon.people.PeopleService


@RestController
@RequestMapping(
        value = ["/people/{token}/geo"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
)
class GeoController(val geoService: GeoService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun resolveAndSaveGeocoding(@RequestBody geoInputDTO: GeoInputDTO): GeoOuputDTO = geoService.resolveSaveCoordinates(geoInputDTO)

}
