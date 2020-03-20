package org.wirvsvirushackathon.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController (val helloService: HelloService) {

    @GetMapping
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String) = helloService.findHelloById(1)
}