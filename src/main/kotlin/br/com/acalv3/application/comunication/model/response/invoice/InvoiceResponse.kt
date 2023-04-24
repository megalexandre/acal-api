package br.com.acalv3.application.comunication.model.response.invoice

import br.com.acalv3.domain.model.Invoice

class InvoiceResponse(
    val id: String,
)

fun Invoice.toInvoiceResponse() = InvoiceResponse(
    id = id.toString(),
)

fun List<Invoice>.toInvoiceResponse() = map{ it.toInvoiceResponse() }
