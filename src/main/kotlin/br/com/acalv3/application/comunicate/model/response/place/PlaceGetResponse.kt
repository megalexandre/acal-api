package br.com.acalv3.application.comunicate.model.response.place

import br.com.acalv3.application.comunicate.model.response.address.AddressGetResponse
import br.com.acalv3.application.comunicate.model.response.address.toGetAddressResponse
import br.com.acalv3.domain.model.Place
import java.util.UUID

class PlaceGetResponse(
    val id: UUID?,
    val number: Long?,
    val hasHydrometer: Boolean,
    val other: String?,
    val letter: String?,
    val address: AddressGetResponse?,
)

fun Place.toGetPlaceResponse() = PlaceGetResponse(
    id = id,
    number = number,
    letter = letter,
    address = address.toGetAddressResponse(),
    hasHydrometer = hasHydrometer,
    other = other,
)