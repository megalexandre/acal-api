package br.com.acalv3.application.comunicate.model.response

import br.com.acalv3.domain.model.Customer
import java.util.*

data class CustomerResponse
    (val id: UUID)

fun Customer.toCustomerResponse() = CustomerResponse(
    id = id
)