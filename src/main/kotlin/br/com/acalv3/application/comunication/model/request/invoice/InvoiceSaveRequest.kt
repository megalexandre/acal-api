package br.com.acalv3.application.comunication.model.request.invoice

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Link
import java.util.UUID

class InvoiceSaveRequest (
    val reference: String? = null,
    val invoiceDetails: List<InvoiceDetailRequest>?,
    val link: LinkRequest
)

class LinkRequest(
    val id: String
)

fun InvoiceSaveRequest.toInvoice() = Invoice(
    id = UUID.randomUUID(),
    reference = reference!!,
    linkId = UUID.fromString(link.id),
).also {
    it.invoiceDetails = invoiceDetails?.toInvoiceDetail() ?: throw RuntimeException()
}

fun List<InvoiceSaveRequest>.toInvoice() = map{ it.toInvoice()}