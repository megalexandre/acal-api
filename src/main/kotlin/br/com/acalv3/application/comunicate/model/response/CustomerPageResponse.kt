package br.com.acalv3.application.comunicate.model.response

import br.com.acalv3.domain.model.Customer
import org.springframework.data.domain.Page
import java.util.*

data class CustomerPageResponse
    (val id: UUID)

fun Customer.toCustomerPageResponse() = CustomerPageResponse(
    id = id
)
fun Page<Customer>.toCustomerPageResponse() = map{ it.toCustomerPageResponse() }