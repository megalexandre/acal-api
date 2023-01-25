package br.com.acalv3.application.comunicate.model.response

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import java.time.LocalDate
import java.util.*

data class GetCustomerResponse(
    val id: UUID?,
    val name: String?,
    val document: String?,
    val personType: PersonTypeEnum?,
    var birthDay: LocalDate? = null,
)

fun Customer.toGetCustomerResponse() = GetCustomerResponse(
    id = id,
    name = name,
    document = document,
    personType = personType,
    birthDay = birthDay
)