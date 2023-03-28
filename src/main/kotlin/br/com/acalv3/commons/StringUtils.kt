package br.com.acalv3.commons

import java.util.regex.Pattern

const val cpfRegex ="(\\d{3})(\\d{3})(\\d{3})(\\d{2})"
const val cpfReplace = "$1.$2.$3-$4"
const val cnpjRegex = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})"
const val cnpjReplace = "$1.$2.$3-$4"
const val cpfSize = 11

fun String.formatDocument() =
    when (this.length) {
        cpfSize -> Pattern.compile(cpfRegex).matcher(this).replaceAll(cpfReplace)
        14 -> Pattern.compile(cnpjRegex).matcher(this).replaceAll(cnpjReplace)
        else -> this
    }

fun String.clearMessage() = run {
    if(this.contains("default message"))

    this.subSequence(
        this.lastIndexOf("default message"), this.length
    )
        .toString().replace("default message", "")
        .replace("[","")
        .replace("]","")
        .trim()

    else ""
}
