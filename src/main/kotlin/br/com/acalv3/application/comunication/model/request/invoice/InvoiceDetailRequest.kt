package br.com.acalv3.application.comunication.model.request.invoice

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.model.InvoiceDetail
import java.math.BigDecimal
import java.util.UUID

class InvoiceDetailRequest (
    val reason: String?,
    val value: BigDecimal?,
)

fun InvoiceDetailRequest.toInvoiceDetail() = InvoiceDetail(
    id = UUID.randomUUID(),
    reason = Reason.byName(reason)?: throw RuntimeException("this reason cant be accepted: $reason"),
    value = value?: throw RuntimeException("value cant be null"),
    isPayed = false,
    dataPayed = null,
)

fun List<InvoiceDetailRequest>.toInvoiceDetail() = map{ it.toInvoiceDetail()}