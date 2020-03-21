package org.wirvsvirushackathon

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.wirvsvirushackathon.people.PeopleNotExistsException
import org.wirvsvirushackathon.people.PeopleNotFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(PeopleNotExistsException::class, PeopleNotFoundException::class)
    fun notFound(ex: Exception) =
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(WsError("Not Found", ex.localizedMessage))

}

data class WsError(val type: String, val message: String)