package org.wirvsvirushackathon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoronaApplication

fun main(args: Array<String>) {
	runApplication<CoronaApplication>(*args)
}
