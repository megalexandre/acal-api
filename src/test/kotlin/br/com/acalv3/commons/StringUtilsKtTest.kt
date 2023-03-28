package br.com.acalv3.commons

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class StringUtilsKtTest {

    @Test
    fun `should masker cpf`() {
        assertEquals("03396885562".formatDocument(), "033.968.855-62")
        assertEquals("01289517029".formatDocument(), "012.895.170-29")
        assertEquals("79276060006".formatDocument(), "792.760.600-06")
        assertEquals("49476108088".formatDocument(), "494.761.080-88")
        assertEquals("18154679086".formatDocument(), "181.546.790-86")
    }

    @Test
    fun clearMessage() {
    }
}