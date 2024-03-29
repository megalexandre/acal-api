package br.com.acalv3.resources.model.dto

import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID


class LegacyUser (
    val name: String,
    val document: String,
    @JsonProperty("person_type")
    val personType: PersonType,
    val number: String?,
    val membershipNumber: Int = 0,
)

fun LegacyUser.toCustomer() = Customer(
    id = UUID.randomUUID(),
    name = name,
    document = document,
    personType = personType,
    phoneNumber = number,
    active = true,
    membershipNumber = membershipNumber,
)
