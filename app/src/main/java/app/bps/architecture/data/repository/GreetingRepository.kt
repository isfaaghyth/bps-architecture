package app.bps.architecture.data.repository

import app.bps.architecture.data.entity.Person

interface GreetingRepository {
    fun welcome(name: String, age: String): Person
}

class GreetingRepositoryImpl : GreetingRepository {

    override fun welcome(name: String, age: String): Person {
        return Person(name, age)
    }

}