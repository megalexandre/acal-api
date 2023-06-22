package br.com.acalv3.resources.model.report

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.commons.toCurrency
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.model.Book
import java.math.BigDecimal
import java.time.format.DateTimeFormatter.ofPattern

class BookReport (
    val value: BigDecimal,
    val currency: String,
    val createdBy: String,
    val createdAt: String,
    val type: String,
    val reason: String,
)

fun Book.toBookReport() = BookReport(
    value = value,
    currency = value.toCurrency(),
    createdBy = createdBy,
    createdAt = createdAt.format(ofPattern(DATE_TIME_FORMAT)),
    type = when(type){
        Type.IN -> "Entrada"
        Type.OUT -> "Saida"
    },
    reason = reason.translate
)

fun List<Book>.toBookReport(): List<BookReport> = map {it.toBookReport()}