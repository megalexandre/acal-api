package br.com.acalv3.application.comunicate.model.request.invoice

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Link
import java.util.UUID

class InvoiceRequest (

    val id: String?,
    val reference: String?,
    val link: Link?,
    val invoiceDetails: List<InvoiceDetailRequest>?,

)

fun InvoiceRequest.toInvoice() = Invoice(
    id = UUID.randomUUID().toString(),
    reference = reference ?: throw RuntimeException(),
    link = link ?: throw RuntimeException(),
    invoiceDetails = invoiceDetails?.toInvoiceDetail() ?: throw RuntimeException(),
)