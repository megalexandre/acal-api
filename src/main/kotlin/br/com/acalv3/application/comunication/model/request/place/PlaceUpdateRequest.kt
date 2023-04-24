package br.com.acalv3.application.comunication.model.request.place

import br.com.acalv3.application.comunication.model.request.address.AddressUpdateRequest
import br.com.acalv3.application.comunication.model.request.address.toAddress
import br.com.acalv3.domain.model.Place
import java.util.UUID
import javax.validation.constraints.NotBlank

class PlaceUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

    val address: AddressUpdateRequest? = null,
    val hasHydrometer: Boolean = false,
    val other: String?,

) : PlaceRequest()

fun PlaceUpdateRequest.toPlace() = Place(
    id = UUID.fromString(id) ?: throw RuntimeException("id can't be null"),
    number = number?: throw RuntimeException("number can't be null"),
    letter = letter?: throw RuntimeException("letter can't be null"),
    address = address?.toAddress() ?: throw RuntimeException("Address can't be null"),
    hasHydrometer = hasHydrometer,
    other = other,
)