package br.com.acalv3.application.comunicate.model.response.customer

import br.com.acalv3.domain.model.Customer

data class SaveUpdateCustomerResponse(
    val id: String
)

fun Customer.toCustomerResponse() = SaveUpdateCustomerResponse(
    id = id
)