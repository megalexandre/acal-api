package br.com.acalv3.application.comunicate.model.response.place

import br.com.acalv3.application.comunicate.model.response.address.AddressGetResponse
import br.com.acalv3.application.comunicate.model.response.address.toGetAddressResponse
import br.com.acalv3.domain.model.Place

class PlaceGetResponse(
    val id: String?,
    val number: Long?,
    val hasHydrometer: Boolean,
    val letter: String?,
    val address: AddressGetResponse?,
)

fun Place.toGetPlaceResponse() = PlaceGetResponse(
    id = id,
    number = number,
    letter = letter,
    address = address.toGetAddressResponse(),
    hasHydrometer = hasHydrometer,
)