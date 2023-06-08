package br.com.acalv3.application.comunication.model.request.invoice

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.domain.model.Invoice
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDateTime
import java.util.UUID

class InvoiceSaveRequest (
    val reference: String? = null,
    val link: LinkRequest,

    @JsonFormat(shape = STRING, pattern = DATE_TIME_FORMAT)
    val emission: LocalDateTime? = null,

    @JsonFormat(shape = STRING, pattern = DATE_TIME_FORMAT)
    val dueDate: LocalDateTime? = null,
)

class LinkRequest(
    val id: String
)

fun InvoiceSaveRequest.toInvoice() = Invoice(
    id = UUID.randomUUID(),
    reference = reference!!,
    linkId = UUID.fromString(link.id),
    emission = emission!!,
    dueDate = dueDate!!,
)

fun List<InvoiceSaveRequest>.toInvoice() = map{ it.toInvoice()}