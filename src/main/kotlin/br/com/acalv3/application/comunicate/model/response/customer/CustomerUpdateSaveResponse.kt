package br.com.acalv3.application.comunicate.model.response.customer

import br.com.acalv3.domain.model.Customer
import java.util.*

data class SaveUpdateCustomerResponse
    (val id: UUID)

fun Customer.toCustomerResponse() = SaveUpdateCustomerResponse(
    id = id
)