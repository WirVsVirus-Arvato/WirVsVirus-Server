package org.wirvsvirushackathon.hello

import org.springframework.stereotype.Service

@Service
class HelloService (val helloRepository: HelloRepository) {

    fun findHelloById(id: Long) = helloRepository.findById(id)
}