package br.com.acalv3.domain.model

import java.math.BigDecimal

class Dashboard(
    val totalCustomer: Long,
    val totalLink: Long,
    val awaitingPaymentInvoice: Long,
    val generatedInvoice: Long,
    val qtdTransactionsToday: Int,
    val valueTransactionsToday: BigDecimal,
)