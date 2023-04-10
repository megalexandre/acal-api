package br.com.acalv3.application.comunicate.model.response.place

import br.com.acalv3.application.comunicate.model.response.address.AddressGetResponse
import br.com.acalv3.application.comunicate.model.response.address.toGetAddressResponse
import br.com.acalv3.domain.model.Place
import java.util.UUID
import org.springframework.data.domain.Page

class PlacePageResponse(
    val id: UUID?,
    val number: Long?,
    val letter: String?,
    val address: AddressGetResponse?,
)

fun Place.toPlacePageResponse() = PlacePageResponse(
    id = id,
    number = number,
    letter = letter,
    address = address.toGetAddressResponse()
)

fun Page<Place>.toPlacePageResponse() = map{ it.toPlacePageResponse() }