package org.wirvsvirushackathon.hello


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@AutoConfigureMockMvc
@WebMvcTest
class HelloControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    def "should return hello world with status 200"() {
        given: 'notificationId and vwUserId'

        when: 'get hello endpoint is called'
        def response = mvc.perform(get('/hello'))

        then: 'expected hello world is returned'
        response.andReturn().response.status == HttpStatus.OK.value()
        response.andExpect(jsonPath('content').value('Hello World'))
    }

    def "should return hello with name and status 200"() {
        given: 'notificationId and vwUserId'

        when: 'get hello endpoint is called'
        def response = mvc.perform(get('/hello?name=john'))

        then: 'expected hello world is returned'
        response.andReturn().response.status == HttpStatus.OK.value()
        response.andExpect(jsonPath('content').value('Hello john'))
    }
}