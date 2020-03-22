package org.wirvsvirushackathon.people

import java.time.ZonedDateTime

data class People(
        val id: Long,
        val token: String,
        val status: PeopleStatus,
        val creationTimestamp: ZonedDateTime
)

data class TokenDto(
        val token: String
)

enum class PeopleStatus {
    CREATED,
    ACTIVE,
    INACTIVE
}