package br.com.acalv3.application.comunication.model.response.invoice

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Link
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDateTime
import org.springframework.data.domain.Page

data class InvoicePageResponse(
    val id: String,
    val reference: String,
    val link: Link?,
    val isPayed: Boolean,
    var invoiceDetails: List<InvoiceDetailResponse>? = null,

    @JsonFormat(shape = STRING, pattern = DATE_TIME_FORMAT)
    val dueDate: LocalDateTime,
) {
    val isOverdue = !isPayed && LocalDateTime.now().isAfter(dueDate)
    val totalValue = invoiceDetails?.sumOf { it.value }
}

fun Invoice.toInvoicePageResponse() = InvoicePageResponse(
    id = id.toString(),
    reference = reference,
    link = link,
    isPayed = isPayed,
    dueDate = dueDate,
    invoiceDetails = invoiceDetails?.toInvoiceDetailResponse(),
)

fun Page<Invoice>.toInvoiceResponse() = map{ it.toInvoicePageResponse() }