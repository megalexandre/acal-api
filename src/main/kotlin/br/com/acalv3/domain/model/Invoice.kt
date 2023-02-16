package br.com.acalv3.domain.model

class Invoice(
    val id: String,
    val referenceMonth: Int,
    val referenceYear: Int,
    val link: Link,
    val invoiceDetails: List<InvoiceDetail>,
)