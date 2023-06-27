package br.com.acalv3.application.comunication.model.request.place

import br.com.acalv3.application.comunication.model.request.address.AddressFilterRequest
import br.com.acalv3.application.comunication.model.request.address.toAddress
import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC
import br.com.acalv3.domain.model.page.PlacePage

class PlacePageRequest(
    val letter: String?,
    val number: Long?,
    val address: AddressFilterRequest?,
    val hasHydrometer: Boolean? ,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: Direction = ASC,

    ): DefaultPageRequest()

fun PlacePageRequest.toPlacePage() = PlacePage(
    letter = letter,
    number = number,
    address = address?.toAddress(),
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
    hasHydrometer = hasHydrometer,
)