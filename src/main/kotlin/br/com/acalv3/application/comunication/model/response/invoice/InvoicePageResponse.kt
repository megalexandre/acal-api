package br.com.acalv3.application.comunication.model.response.invoice

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Link
import org.springframework.data.domain.Page

data class InvoicePageResponse(
    val id: String,
    val reference: String,
    val link: Link?,
    val isPayed: Boolean,
    var invoiceDetails: List<InvoiceDetailResponse>? = null,
)

fun Invoice.toInvoicePageResponse() = InvoicePageResponse(
    id = id.toString(),
    reference = reference,
    link = link,
    isPayed = isPayed,
    invoiceDetails = invoiceDetails?.toInvoiceDetailResponse(),
)

fun Page<Invoice>.toInvoiceResponse() = map{ it.toInvoicePageResponse() }