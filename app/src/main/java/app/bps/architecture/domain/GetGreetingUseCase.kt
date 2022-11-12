package app.bps.architecture.domain

import app.bps.architecture.data.repository.GreetingRepository
import app.bps.architecture.domain.model.GreetingUiModel

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