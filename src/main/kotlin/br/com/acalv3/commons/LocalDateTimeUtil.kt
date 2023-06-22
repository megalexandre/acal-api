package br.com.acalv3.commons

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.application.comunication.Fixture.Companion.REFERENCE
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.defaultDateFormat(format: String = DATE_FORMAT): String =
    this.toLocalDate().format(DateTimeFormatter.ofPattern(format))

fun LocalDateTime.defaultFormat(format: String = DATE_TIME_FORMAT): String =
    this.format(DateTimeFormatter.ofPattern(format))

fun LocalDateTime.referenceFormat(format: String = REFERENCE): String =
    this.format(DateTimeFormatter.ofPattern(format))


