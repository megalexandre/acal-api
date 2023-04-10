package br.com.acalv3.application.comunicate.model.request.place

import br.com.acalv3.application.comunicate.model.request.address.AddressFilterRequest
import br.com.acalv3.application.comunicate.model.request.address.AddressSaveRequest
import br.com.acalv3.application.comunicate.model.request.address.toAddress
import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.PlacePage

class PlacePageRequest(
    val letter: String?,
    val number: Long?,
    val address: AddressFilterRequest?,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",

    ): DefaultPageRequest()

fun PlacePageRequest.toPlacePage() = PlacePage(
    letter = letter,
    number = number,
    address = address?.toAddress(),
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)