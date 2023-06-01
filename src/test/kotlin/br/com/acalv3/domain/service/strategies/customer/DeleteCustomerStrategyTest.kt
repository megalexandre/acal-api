package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.stub.customerStub
import br.com.acalv3.stub.linkStub
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class DeleteCustomerStrategyTest{

    private val repository = mockk<LinkService>()
    private val saveCustomerStrategy = DeleteCustomerStrategy(repository)

    @Test
    fun `should has delete action`(){
        assertEquals(saveCustomerStrategy.action(), DELETE)
    }

    @Test
    fun `when customer has link should throw exception`(){
        val link = linkStub()
        val customer = customerStub()

        every {
            repository.findByCustomerId(any())
        } returns link

        val exception = assertThrows<RuntimeException> {
            saveCustomerStrategy.can(customer)
        }

        assertEquals(
            exception.message,
            "O usúario não pode ser apagado porque está associado as ligações: oh hay, Núm: 1 | A")
    }

    @Test
    fun `when customer has no one link should not throw exception`(){
        val customer = customerStub()

        every {
            repository.findByCustomerId(any())
        } returns null

        assertDoesNotThrow  {
            saveCustomerStrategy.can(customer)
        }
    }

}