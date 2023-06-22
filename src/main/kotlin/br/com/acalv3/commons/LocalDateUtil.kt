package br.com.acalv3.commons

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate?.toLocalDateTimeStartedDefaultFormat(): String? =
    this?.atStartOfDay()?.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))

fun LocalDate?.toLocalDateTimeFinishDefaultFormat(): String? =
    this?.atStartOfDay()?.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
