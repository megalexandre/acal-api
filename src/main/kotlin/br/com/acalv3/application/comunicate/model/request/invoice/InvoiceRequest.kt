package br.com.acalv3.application.comunicate.model.request.invoice

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Link
import java.util.UUID

class InvoiceRequest (

    val id: String?,
    val referenceMonth: Int?,
    val referenceYear: Int?,
    val link: Link?,
    val invoiceDetails: List<InvoiceDetailRequest>?,

)

fun InvoiceRequest.toInvoice() = Invoice(
    id = UUID.randomUUID().toString(),
    referenceMonth = referenceMonth ?: throw RuntimeException(),
    referenceYear = referenceYear ?: throw RuntimeException(),
    link = link ?: throw RuntimeException(),
    invoiceDetails = invoiceDetails?.toInvoiceDetail() ?: throw RuntimeException(),
)