package br.com.acalv3.commons

import br.com.acalv3.application.comunicate.Fixture.Companion.MONTH
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale
import java.util.regex.Pattern

const val cpfRegex ="(\\d{3})(\\d{3})(\\d{3})(\\d{2})"
const val cpfReplace = "$1.$2.$3-$4"
const val cnpjRegex = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})"
const val cnpjReplace = "$1.$2.$3.$4-$5"
const val cpfSize = 11
const val defaultMessage = "default message"
const val referenceRegex = "^(0[1-9]|1[0-2])(18|19|20|21)\\d{2}\$"
const val startMonth = 0
const val finishMonth = 2
const val startYear = 2
const val finishYear = 6
const val firstDayOfMonth = 1

fun String.reference(): String =
    when(this.isReference()) {
        true -> LocalDate.of(
            this.substring(startYear,finishYear).toInt(),
            this.substring(startMonth,finishMonth).toInt(), firstDayOfMonth).format(ofPattern(MONTH))
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } +
            "/${this.substring(startYear,finishYear).toInt()}"
        false -> this
    }

fun String.isReference(): Boolean =
    when(this.length){
        6 -> Pattern.compile(referenceRegex).matcher(this).matches()
        else -> false
    }


fun String.formatDocument(): String =
    when (this.length) {
        cpfSize -> Pattern.compile(cpfRegex).matcher(this).replaceAll(cpfReplace)
        14 -> Pattern.compile(cnpjRegex).matcher(this).replaceAll(cnpjReplace)
        else -> this
    }

fun String.clearMessage() = run {
    if(this.contains(defaultMessage))

    this.subSequence(
        this.lastIndexOf(defaultMessage), this.length
    )
        .toString().replace(defaultMessage, "")
        .replace("[","")
        .replace("]","")
        .trim()

    else ""
}
