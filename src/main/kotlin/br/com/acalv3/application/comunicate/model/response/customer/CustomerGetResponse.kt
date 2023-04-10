package br.com.acalv3.application.comunicate.model.response.customer

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.UUID

data class CustomerGetResponse(
    val id: UUID?,
    val name: String?,
    val document: String?,
    val phoneNumber: String?,
    val personType: PersonType?,
    @JsonFormat(pattern = DATE_FORMAT)
    val birthDay: LocalDate? = null,
    val active: Boolean
)

fun Customer.toGetCustomerResponse() = CustomerGetResponse(
    id = id,
    name = name,
    document = document,
    personType = personType,
    birthDay = birthDay,
    phoneNumber = phoneNumber,
    active = active,
)