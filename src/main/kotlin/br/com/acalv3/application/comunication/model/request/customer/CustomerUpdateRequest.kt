package br.com.acalv3.application.comunication.model.request.customer

import br.com.acalv3.domain.enumeration.PersonType
import br.com.acalv3.domain.model.Customer
import java.util.UUID
import javax.validation.constraints.NotBlank

data class CustomerUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

    ) : CustomerRequest()

fun CustomerUpdateRequest.toCustomer() = Customer(
    id = UUID.fromString(id) ?: throw RuntimeException("id can't be null"),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    document = document?.trim() ?: throw RuntimeException("document can't be null"),
    personType = PersonType.valueOf(personType ?: throw RuntimeException("person type can't be null")),
    phoneNumber = phoneNumber?.trim(),
    birthDay = birthDay,
    active = true,
)