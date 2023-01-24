package br.com.acalv3.commons

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
