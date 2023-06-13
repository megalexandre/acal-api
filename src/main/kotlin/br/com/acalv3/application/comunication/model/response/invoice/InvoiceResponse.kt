package br.com.acalv3.application.comunication.model.response.invoice

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.application.comunication.model.response.link.LinkGetResponse
import br.com.acalv3.application.comunication.model.response.link.toLinkGetResponse
import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.InvoiceDetail
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class InvoiceResponse(
    val id: String,
    val reference: String,
    val isPayed: Boolean = true,
    var invoiceDetails: List<InvoiceDetailResponse>? = null,
    @JsonFormat(shape = STRING, pattern = DATE_TIME_FORMAT)
    val emission: LocalDateTime,
    @JsonFormat(shape = STRING, pattern = DATE_TIME_FORMAT)
    val dueDate: LocalDateTime,
    val link: LinkGetResponse?,
)

class InvoiceDetailResponse(
    val id: UUID,
    val reason: Reason,
    val value: BigDecimal,
    var isPayed: Boolean,
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    var dataPayed: LocalDateTime?,
)

fun Invoice.toInvoiceResponse() = InvoiceResponse(
    id = id.toString(),
    reference = reference,
    link = link?.toLinkGetResponse(),
    isPayed = isPayed,
    invoiceDetails = invoiceDetails?.toInvoiceDetailResponse(),
    emission = emission,
    dueDate = dueDate,
)

fun InvoiceDetail.toInvoiceDetailResponse() = InvoiceDetailResponse(
    id = id,
    reason = reason,
    value = value,
    isPayed = isPayed,
    dataPayed = dataPayed,
)

fun List<Invoice>.toInvoiceResponse(): List<InvoiceResponse> = map{ it.toInvoiceResponse() }
fun List<InvoiceDetail>.toInvoiceDetailResponse(): List<InvoiceDetailResponse> = map{ it.toInvoiceDetailResponse() }