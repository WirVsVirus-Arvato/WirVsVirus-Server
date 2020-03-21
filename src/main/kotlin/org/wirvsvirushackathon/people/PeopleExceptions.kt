package org.wirvsvirushackathon.people

class PeopleNotFoundException(id: Long) : RuntimeException("Person with id=$id not found")
class PeopleNotExistsException(token: String) : RuntimeException("Person with token=$token not found")