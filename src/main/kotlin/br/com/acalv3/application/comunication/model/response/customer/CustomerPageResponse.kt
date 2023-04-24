package br.com.acalv3.application.comunication.model.response.customer

import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.model.Customer
import java.util.UUID
import org.springframework.data.domain.Page

data class CustomerPageResponse(
    val id: UUID?,
    val name: String?,
    val document: String?,
    val personType: PersonType,
)
fun Customer.toCustomerPageResponse() = CustomerPageResponse(
    id = id,
    name = name,
    document = document,
    personType = personType
)
fun Page<Customer>.toCustomerPageResponse() = map{ it.toCustomerPageResponse() }