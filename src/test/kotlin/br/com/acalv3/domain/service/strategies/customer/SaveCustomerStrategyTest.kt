package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.stub.customerStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class SaveCustomerStrategyTest{

    private val repository = mockk<CustomerRepository>()
    private val saveCustomerStrategy = SaveCustomerStrategy(repository)

    @Test
    fun `should has save action`(){
        assertEquals(saveCustomerStrategy.action(), SAVE)
    }


    @Test
    fun `when receive a not founded customer must not throw exception`(){
        val customer = customerStub()

        every {
            repository.findByDocument(any())
        } returns null

        assertDoesNotThrow {
            saveCustomerStrategy.can(customer)
        }

        verify(exactly = 1) {
            repository.findByDocument(any())
        }
    }

    @Test
    fun `when receive a existent customer must not throw exception`(){
        val customer = customerStub()

        every {
            repository.findByDocument(any())
        } returns customer

        val exception = assertThrows<RuntimeException> {
            saveCustomerStrategy.can(customer)
        }

        assertEquals(
            exception.message, "o documento 956.807.180-65 já está cadastrado para o usuário: customer-stub-test")

    }

}