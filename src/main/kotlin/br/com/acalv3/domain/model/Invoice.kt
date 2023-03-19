package br.com.acalv3.domain.model

import java.util.UUID

class Invoice(
    val id: UUID,
    val reference: String,
    val link: Link,

) {
    var invoiceDetails: List<InvoiceDetail>? = null
}