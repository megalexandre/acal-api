package br.com.acalv3.application.comunication.model.request.invoice

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Link
import java.util.UUID

class InvoiceTest(
    val id: String? = null,
    val reference: String? = null,
    val invoiceDetails: List<InvoiceDetailRequest>?,
)

class InvoiceRequest (
    val id: String? = null,
    val reference: String? = null,
    val invoiceDetails: List<InvoiceDetailRequest>?,
)

fun InvoiceRequest.toInvoice(link: Link) = Invoice(
    id = UUID.randomUUID(),
    reference = reference!!,
    link = link,
).also {
    it.invoiceDetails = invoiceDetails?.toInvoiceDetail() ?: throw RuntimeException()
}

fun List<InvoiceRequest>.toInvoice(link: Link) = map{ it.toInvoice(link)}