package org.wirvsvirushackathon.people

import org.springframework.stereotype.Service

@Service
class PeopleService(val peopleRepository: PeopleRepository) {
    val tokenChars: String = "ABCDEFGHIJKMNPQRSTUVWXYZ0123456789"

    fun createNewInfectedPeople(): TokenDto {
        var tokenExists = true
        var token: String = ""
        while (tokenExists) {
            token = generateToken()
            tokenExists = peopleRepository.tokenAlreadyExists(token)
        }

        return peopleRepository.create(token)
    }

    fun getPeopleByToken(token: String) =
            peopleRepository.getPeopleByToken(token)

    private fun generateToken(): String {
        val builder = StringBuilder()
        var count = 6

        while (count-- != 0) {
            val character = (Math.random() * tokenChars.length).toInt()
            builder.append(tokenChars[character])
        }

        return builder.toString().chunked(3).joinToString("-")
    }

    fun activateByToken(token: String) =
            peopleRepository.setStatus(token, PeopleStatus.ACTIVE)

    fun deactivateByToken(token: String) =
            peopleRepository.setStatus(token, PeopleStatus.INACTIVE)
}