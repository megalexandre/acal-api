package br.com.acalv3.application.comunicate.model.response.customer

import br.com.acalv3.domain.model.Customer
import org.springframework.data.domain.Page

data class CustomerPageResponse(
    val id: String?,
    val name: String?,
    val document: String?,
)

fun Customer.toCustomerPageResponse() = CustomerPageResponse(
    id = id,
    name = name,
    document = document,
)
fun Page<Customer>.toCustomerPageResponse() = map{ it.toCustomerPageResponse() }