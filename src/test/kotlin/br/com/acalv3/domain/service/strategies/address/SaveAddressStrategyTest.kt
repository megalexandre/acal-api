package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.stub.addressStub
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class SaveAddressStrategyTest{

    private val repository = mockk<AddressRepository>()
    private val strategy = SaveAddressStrategy(repository)

    @Test
    fun `should has SAVE action`(){
        assertEquals(strategy.action(), SAVE)
    }

    @Test
    fun `cant duplicate label`(){
        val address = addressStub()

        every {
            repository.findByName(any())
        } returns address

        val exception = assertThrows<RuntimeException> {
            strategy.can(address)
        }

        assertEquals(
            exception.message,"O Endereço oh hay já está cadastrado"
        )
    }

    @Test
    fun `should save when repository return null`(){
        val address = addressStub()

        every {
            repository.findByName(any())
        } returns null

        assertDoesNotThrow {
            strategy.can(address)
        }
    }

}