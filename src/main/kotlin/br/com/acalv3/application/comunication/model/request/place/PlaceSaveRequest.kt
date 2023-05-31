package br.com.acalv3.application.comunication.model.request.place

import br.com.acalv3.domain.model.Place
import java.util.UUID

class PlaceSaveRequest(
    val address: Address,
    val hasHydrometer: Boolean = false,
    val other: String?,
): PlaceRequest()

class Address(
    val id: UUID
)

fun PlaceSaveRequest.toPlace() = Place(
    id = UUID.randomUUID(),
    letter = letter?: "A",
    number = number?: throw RuntimeException("number can't be null"),
    addressId = address.id,
    hasHydrometer = hasHydrometer,
    other = other,
)

