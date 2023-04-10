package br.com.acalv3.stub

import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.enumeration.PersonType.PERSON
import br.com.acalv3.domain.model.Customer
import java.time.LocalDate
import java.time.LocalDate.now
import java.util.UUID
import java.util.UUID.randomUUID

fun customerStub(
    id: UUID = randomUUID(),
    name: String = "customer-stub-test",
    personType: PersonType = PERSON,
    phoneNumber: String = "71988872749",
    document: String = "03386558662",
    birthDay: LocalDate = now(),
    active: Boolean = true
) = Customer(
     id = id,
     name = name,
     personType = personType,
     phoneNumber = phoneNumber,
     document = document,
     birthDay = birthDay,
     active = active
)