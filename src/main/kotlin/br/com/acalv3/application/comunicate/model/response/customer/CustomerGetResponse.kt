package br.com.acalv3.application.comunicate.model.response.customer

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.application.comunicate.Fixture.Companion.DEFAULT_DATE_TIME_FORMAT
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*

data class GetCustomerResponse(
    val id: UUID?,
    val name: String?,
    val document: String?,
    val phoneNumber: String?,
    val personType: PersonTypeEnum?,
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    var birthDay: LocalDate? = null,
)

fun Customer.toGetCustomerResponse() = GetCustomerResponse(
    id = id,
    name = name,
    document = document,
    personType = personType,
    birthDay = birthDay,
    phoneNumber = phoneNumber
)