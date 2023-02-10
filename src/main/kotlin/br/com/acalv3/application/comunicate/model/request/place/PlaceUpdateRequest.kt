package br.com.acalv3.application.comunicate.model.request.place

import br.com.acalv3.application.comunicate.model.request.address.toAddress
import br.com.acalv3.domain.model.Place
import javax.validation.constraints.NotBlank

class PlaceUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

) : PlaceRequest()

fun PlaceUpdateRequest.toPlace() = Place(
    id = id?: throw RuntimeException("id can't be null"),
    number = number?: throw RuntimeException("number can't be null"),
    letter = letter?: throw RuntimeException("letter can't be null"),
    address = address?.toAddress() ?: throw RuntimeException("Address can't be null"),
)