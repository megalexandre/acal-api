package br.com.acalv3.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime
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
}