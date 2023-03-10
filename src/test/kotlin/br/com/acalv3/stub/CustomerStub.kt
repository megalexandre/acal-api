package br.com.acalv3.stub

import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.model.Customer
import java.time.LocalDate
import java.util.*

fun customerStub(
    id: UUID = UUID.randomUUID(),
    name: String = "customer-stub-test",
    personType: PersonType = PersonType.PERSON,
    phoneNumber: String = "71988872749",
    document: String = "03386558662",
    birthDay: LocalDate = LocalDate.now(),
    active: Boolean = true
) = Customer(
     id = id.toString(),
     name = name,
     personType = personType,
     phoneNumber = phoneNumber,
     document = document,
     birthDay = birthDay,
     active = active
)