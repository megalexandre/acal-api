package br.com.acalv3.stub

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.InvoiceDetail
import br.com.acalv3.domain.model.Link
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import java.util.UUID.randomUUID

fun invoiceStub(
    id: UUID = randomUUID(),
    reference: String = "022023",
    linkId: UUID = randomUUID(),
    link: Link? = null,
    value: BigDecimal = BigDecimal.ZERO,
    isPayed: Boolean = false,
    emission: LocalDateTime = LocalDateTime.now(),
    dueDate: LocalDateTime = LocalDateTime.now(),
) = Invoice(
    id = id,
    reference = reference,
    linkId = linkId,
    link = link,
    isPayed = isPayed,
    emission = emission,
    dueDate = dueDate,
    value = value,
)

fun invoiceDetailStub(
    id: UUID = randomUUID(),
    reason: Reason = Reason.CATEGORY,
    value: BigDecimal = BigDecimal.TEN,
    isPayed: Boolean = false,
    dataPayed: LocalDateTime ?= null,
) = InvoiceDetail(
     id = id,
     reason = reason,
     value = value,
     isPayed = isPayed,
     dataPayed = dataPayed,
)