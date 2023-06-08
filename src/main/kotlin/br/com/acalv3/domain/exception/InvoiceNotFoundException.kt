package br.com.acalv3.domain.exception

class InvoiceNotFoundException(

    override val message: String

) : RuntimeException(message)