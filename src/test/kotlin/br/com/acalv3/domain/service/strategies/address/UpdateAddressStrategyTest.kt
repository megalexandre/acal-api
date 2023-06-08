package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.stub.addressStub
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class UpdateAddressStrategyTest {

    private val repository = mockk<AddressRepository>()
    private val strategy = UpdateAddressStrategy(repository)

    @Test
    fun `should has UPDATE action`(){
        assertEquals(strategy.action(), UPDATE)
    }

    @Test
    fun `when address exists the id should be the same `(){
        val address = addressStub()

        every {
            repository.findByName(any())
        } returns address

        assertDoesNotThrow {
            strategy.can(address)
        }
    }

    @Test
    fun `when the name was saved an other address should throw exception`(){
        val originalAddress = addressStub(name = "Raios duplos")
        val newAddress = addressStub(name = "Raios duplos")

        every {
            repository.findByName(any())
        } returns originalAddress

        val exception = assertThrows<RuntimeException> {
            strategy.can(newAddress)
        }

        assertEquals(exception.message, "O Endereço Raios duplos já está cadastrado")
    }
}