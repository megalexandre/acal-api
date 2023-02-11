package br.com.acalv3.application.comunicate.model.request.customer

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import java.util.UUID

class CustomerSaveRequest: CustomerRequest()

fun CustomerSaveRequest.toCustomer() = Customer(
    id = UUID.randomUUID().toString(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    document = document?.trim()  ?: throw RuntimeException("document can't be null"),
    phoneNumber = phoneNumber?.trim(),
    personType = PersonTypeEnum.valueOf(personType ?: throw RuntimeException("personType can't be null")),
    birthDay = birthDay
)