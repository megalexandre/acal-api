package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.stub.customerStub
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class UpdateCustomerStrategyTest{

    private val repository = mockk<CustomerRepository>()
    private val updateCustomerStrategy = UpdateCustomerStrategy(repository)

    @Test
    fun `should has save update`(){
        assertEquals(updateCustomerStrategy.action(), UPDATE)
    }

    @Test
    fun `when customer not exist should throw exception`(){
        val customer = customerStub()

        every {
            repository.findByDocument(any())
        } returns null

        val exception = assertThrows<RuntimeException> {
            updateCustomerStrategy.can(customer)
        }

        assertEquals(exception.message, "O documento 956.807.180-65 não está cadastrado")
    }

    @Test
    fun `when customer exists request identify must be the same saved`(){
        val customer = customerStub()

        every {
            repository.findByDocument(any())
        } returns customer

        assertDoesNotThrow {
            updateCustomerStrategy.can(customer)
        }

    }

    @Test
    fun `when customer exists and request has a different identify should throw exception`(){
        val requestCustomer = customerStub(document = "77105500026")
        val savedCustomer = customerStub(document = "21666321060")

        every {
            repository.findByDocument(any())
        } returns savedCustomer

        val exception = assertThrows<RuntimeException> {
            updateCustomerStrategy.can(requestCustomer)
        }

        assertEquals(
            exception.message, "O documento 216.663.210-60 já está cadastrado para o usuário: customer-stub-test")
    }

}