package br.com.acalv3.domain.model

class Dashboard(
    val totalCustomer: Long,
    val totalLink: Long,
    val awaitingPaymentInvoice: Long,
    val generatedInvoice: Long,
)