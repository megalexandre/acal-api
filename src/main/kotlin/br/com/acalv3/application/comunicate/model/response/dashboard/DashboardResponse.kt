package br.com.acalv3.application.comunicate.model.response.dashboard

import br.com.acalv3.domain.model.Dashboard

class DashboardResponse (
    val totalCustomer: Long,
    val totalLink: Long,
    val invoicing: Long
)

fun Dashboard.toDashboardResponse() = DashboardResponse(
    totalCustomer = totalCustomer,
    totalLink = totalLink,
    invoicing = invoicing,
)
