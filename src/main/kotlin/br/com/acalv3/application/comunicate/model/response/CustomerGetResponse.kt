package br.com.acalv3.application.comunicate.model.response

import br.com.acalv3.domain.model.Customer
import java.util.*

data class GetCustomerResponse
    (val id: UUID)

fun Customer.toGetCustomerResponse() = GetCustomerResponse(
    id = id
)