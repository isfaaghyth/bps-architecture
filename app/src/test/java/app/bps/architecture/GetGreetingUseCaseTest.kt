package app.bps.architecture

import app.bps.architecture.data.entity.Person
import app.bps.architecture.data.repository.GreetingRepository
import app.bps.architecture.domain.GetGreetingUseCase
import app.bps.architecture.domain.GetGreetingUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGreetingUseCaseTest {

    private var repository = mock(GreetingRepository::class.java)

    private val useCase: GetGreetingUseCase = GetGreetingUseCaseImpl(repository)

    @Test
    fun `seharusnya muncul data yang sesuai diharapkan`() {
        // Given
        val name = "Isfa"
        val age = "24"

        val greetingMessage = "Hi! %s, umur kamu sekarang %s tahun."
        val expectedValue = greetingMessage.format(name, age)

        Mockito.`when`(repository.welcome(name, age)).thenReturn(Person(name, age))

        // When
        val result = useCase.welcome(name, age)

        // Then
        assertEquals(expectedValue, result.message)
    }

    @Test
    fun `seharusnya muncul data tidak sesuai diharapkan`() {
        // Given
        val name = "Isfa"
        val age = "24"

        val greetingMessage = "Hai, selamat datang!"
        val expectedValue = greetingMessage.format(name, age)

        Mockito.`when`(repository.welcome(name, age)).thenReturn(Person(name, age))

        // When
        val result = useCase.welcome(name, age)

        // Then
        assert(expectedValue != result.message)
    }

}