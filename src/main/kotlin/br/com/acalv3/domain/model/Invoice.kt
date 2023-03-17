package br.com.acalv3.domain.model

class Invoice(
    val id: String,
    val reference: String,
    val link: Link,
    val invoiceDetails: List<InvoiceDetail>,
)