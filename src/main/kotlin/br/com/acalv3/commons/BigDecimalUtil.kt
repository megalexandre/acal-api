package br.com.acalv3.commons

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal?.toCurrency(): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(
        when(this){
            null -> ZERO
            else -> this
        }
    )