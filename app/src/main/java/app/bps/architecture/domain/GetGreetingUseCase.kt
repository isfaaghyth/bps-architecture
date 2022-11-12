package app.bps.architecture.domain

import app.bps.architecture.data.entity.Person
import app.bps.architecture.data.repository.GreetingRepository
import app.bps.architecture.domain.model.GreetingUiModel

/**
 * UseCase (Domain):
 *
 * Melakukan transformasi data dari repository
 */
interface GetGreetingUseCase {
    fun welcome(name: String, age: String): GreetingUiModel
}

class GetGreetingUseCaseImpl(
    private val repository: GreetingRepository
) : GetGreetingUseCase {

    override fun welcome(name: String, age: String): GreetingUiModel {
        val result = repository.welcome(name, age)
        return GreetingUiModel(result.toString())
    }

}