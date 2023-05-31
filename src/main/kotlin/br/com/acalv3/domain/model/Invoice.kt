package br.com.acalv3.domain.model

import java.util.UUID

class Invoice(
    val id: UUID,
    val reference: String,
    val linkId: UUID,
    val link: Link? = null,
    val payout: Boolean = true,
) {
    var invoiceDetails: List<InvoiceDetail>? = null
}