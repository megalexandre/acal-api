package br.com.acalv3.application.comunicate.model.response.invoice

import br.com.acalv3.domain.model.Invoice

class InvoiceResponse(
    val id: String,
)

fun Invoice.toInvoiceResponse() = InvoiceResponse(
    id = id,
)

fun List<Invoice>.toInvoiceResponse() = map{ it.toInvoiceResponse() }
