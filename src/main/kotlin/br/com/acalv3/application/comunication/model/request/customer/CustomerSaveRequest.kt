package br.com.acalv3.application.comunication.model.request.customer

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate
import java.util.UUID

class CustomerSaveRequest(

    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    override var birthDay: LocalDate? = null,

): CustomerRequest()

fun CustomerSaveRequest.toCustomer() = Customer(
    id = UUID.randomUUID(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    document = document?.trim()  ?: throw RuntimeException("document can't be null"),
    phoneNumber = phoneNumber?.trim(),
    personType = PersonType.valueOf(personType ?: throw RuntimeException("personType can't be null")),
    birthDay = birthDay,
    active = true,
    membershipNumber = membershipNumber,
)