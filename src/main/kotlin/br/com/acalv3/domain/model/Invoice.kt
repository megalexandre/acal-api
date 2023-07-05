package br.com.acalv3.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.UUID

data class Invoice(
    val id: UUID,
    val reference: String,
    val linkId: UUID,
    val link: Link? = null,
    var value: BigDecimal,
    val isPayed: Boolean,

    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
) {
    var invoiceDetails: List<InvoiceDetail>? = null

    val status: String = when {
        isPayed -> "payed"
        now().isBefore(dueDate) -> "awaiting"
        now().isAfter(dueDate.plusMonths(1)) -> "atRiskOfBeingCanceled"
        else -> "accountOverdue"
    }

}