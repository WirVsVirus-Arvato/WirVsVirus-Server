package org.wirvsvirushackathon.people

import java.awt.Point
import java.time.ZonedDateTime

data class People(
        val id: Long,
        val token: String,
        val status: PeopleStatus,
        val creationTimestamp: ZonedDateTime
)

enum class PeopleStatus {
    CREATED,
    ACTIVE,
    INACTIVE
}