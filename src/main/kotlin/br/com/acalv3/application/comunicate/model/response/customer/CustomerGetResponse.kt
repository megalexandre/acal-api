package br.com.acalv3.application.comunicate.model.response.customer

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class CustomerGetResponse(
    val id: String?,
    val name: String?,
    val document: String?,
    val phoneNumber: String?,
    val personType: PersonTypeEnum?,
    @JsonFormat(pattern = DATE_FORMAT)
    var birthDay: LocalDate? = null,
)

fun Customer.toGetCustomerResponse() = CustomerGetResponse(
    id = id,
    name = name,
    document = document,
    personType = personType,
    birthDay = birthDay,
    phoneNumber = phoneNumber
)