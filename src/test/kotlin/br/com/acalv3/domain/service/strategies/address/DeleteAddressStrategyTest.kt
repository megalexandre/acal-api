package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.service.PlaceService
import br.com.acalv3.stub.addressStub
import br.com.acalv3.stub.placeStub
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class DeleteAddressStrategyTest{

    private val service = mockk<PlaceService>()
    private val strategy = DeleteAddressStrategy(service)

    @Test
    fun `should has delete action`(){
        assertEquals(strategy.action(), Action.DELETE)
    }

    @Test
    fun `when address has a place should throw exception`(){
        val address = addressStub()
        val place = placeStub()

        every {
            service.findByAddress(any())
        } returns place

        val exception = assertThrows<RuntimeException> {
            strategy.can(address)
        }

        assertEquals(
            exception.message,
            "Esse logradouro está vinculado ao Endereco: oh hay, 1A"
        )
    }

    @Test
    fun `when address not has a place should do not throw`(){
        val address = addressStub()

        every {
            service.findByAddress(any())
        } returns null

        assertDoesNotThrow {
            strategy.can(address)
        }
    }

}