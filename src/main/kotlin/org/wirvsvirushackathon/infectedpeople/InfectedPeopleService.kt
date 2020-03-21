package org.wirvsvirushackathon.infectedpeople

import org.springframework.stereotype.Service

@Service
class InfectedPeopleService(val infectedPeopleRepository: InfectedPeopleRepository) {
    val tokenChars: String = "ABCDEFGHIJKMNPQRSTUVWXYZ0123456789"

    fun createNewInfectedPeople(): InfectedPeople {
        var tokenExists = true
        var token: String = ""
        while (tokenExists) {
            token = generateToken()
            tokenExists = infectedPeopleRepository.tokenAlreadyExists(token)
        }

        return infectedPeopleRepository.create(token)
    }

    private fun generateToken(): String {
        val builder = StringBuilder()
        var count = 6

        while (count-- != 0) {
            val character = (Math.random() * tokenChars.length).toInt()
            builder.append(tokenChars[character])
        }

        return builder.toString()
    }
}