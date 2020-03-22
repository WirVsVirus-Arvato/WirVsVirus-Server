package org.wirvsvirushackathon.people.geocoding

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URLDecoder


@Service
class GeoService() {

    fun resolveSaveCoordinates(geoInputDTO: GeoInputDTO): GeoOuputDTO  {
        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE)
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        val url = URLDecoder.decode ("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?prox=51.67299%2C8.3446143%2C250&mode=retrieveAddresses&maxresults=1&gen=9&apiKey=hMMlYFxGvdEnS2fEbFt0ocxTH5Du4D7DWeZ-mZqLFc8")
 //       var response = RestTemplate().getForObject(
   //            url.toString(),

     //   )

        throw NotImplementedError()
//        var firstAdress = response view?.get(0)?.result?.get(0)?.location?.address
  //      return GeoOuputDTO(firstAdress?.city, 10, 100)
    }
}
