package br.com.acalv3.resources.model.dto

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


class LegacyUser (
    val name: String,
    val document: String,
    @JsonProperty("person_type")
    val personType: PersonTypeEnum,
    val number: String?,
)

fun LegacyUser.toCustomer() = Customer(
    id = UUID.randomUUID(),
    name = name,
    document = document,
    personType = personType,
    phoneNumber = number,
)