package br.com.acalv3.application.comunicate.model.request.invoice

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.model.InvoiceDetail
import java.math.BigDecimal

class InvoiceDetailRequest (
    val id: String?,
    val reason: String?,
    val value: BigDecimal?,
)

fun InvoiceDetailRequest.toInvoiceDetail() = InvoiceDetail(
    id = id ?: throw RuntimeException(),
    reason = Reason.byName(reason)?: throw RuntimeException() ,
    value = value?: throw RuntimeException(),
)

fun List<InvoiceDetailRequest>.toInvoiceDetail() = map{ it.toInvoiceDetail()}