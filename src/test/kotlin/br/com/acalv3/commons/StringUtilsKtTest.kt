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
    fun `should return month by name`() {
        assertEquals("012023".reference(), "Janeiro/2023")
        assertEquals("022023".reference(), "Fevereiro/2023")
        assertEquals("032023".reference(), "Março/2023")
        assertEquals("042023".reference(), "Abril/2023")
        assertEquals("052023".reference(), "Maio/2023")
        assertEquals("062023".reference(), "Junho/2023")
        assertEquals("072023".reference(), "Julho/2023")
        assertEquals("082023".reference(), "Agosto/2023")
        assertEquals("092023".reference(), "Setembro/2023")
        assertEquals("102023".reference(), "Outubro/2023")
        assertEquals("112023".reference(), "Novembro/2023")
        assertEquals("122023".reference(), "Dezembro/2023")
    }

    @Test
    fun `should considerate is reference`() {
        assertTrue("012023".isReference())
        assertFalse("002023".isReference())
    }

    @Test
    fun `should original value is string is not reference`() {
        assertEquals("outra coisa".reference(), "outra coisa")
    }


}