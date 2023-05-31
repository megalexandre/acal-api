package br.com.acalv3.application.comunication.model.response.dashboard

import br.com.acalv3.domain.model.Dashboard

class DashboardResponse (
    val totalLink: Long,
    val totalCustomer: Long,
    val awaitingPaymentInvoice: Long,
    val generatedInvoice: Long,
)

fun Dashboard.toDashboardResponse() = DashboardResponse(
    totalCustomer = totalCustomer,
    totalLink = totalLink,
    awaitingPaymentInvoice = awaitingPaymentInvoice,
    generatedInvoice = generatedInvoice,
)
