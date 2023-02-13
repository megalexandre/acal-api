package br.com.acalv3.application.comunicate.model.request.place

import br.com.acalv3.application.comunicate.model.request.address.AddressUpdateRequest
import br.com.acalv3.application.comunicate.model.request.address.toAddress
import br.com.acalv3.domain.model.Place
import java.util.UUID

class PlaceSaveRequest(
    val address: AddressUpdateRequest? = null,
): PlaceRequest()

fun PlaceSaveRequest.toPlace() = Place(
    id = UUID.randomUUID().toString(),
    letter = letter?: "A",
    number = number?: throw RuntimeException("number can't be null"),
    address = address?.toAddress() ?: throw RuntimeException("address can't be null"),
)