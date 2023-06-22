package br.com.acalv3.application.comunication.model.request.address

import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC
import br.com.acalv3.domain.model.page.AddressPage

data class AddressPaginateRequest(

    val name: String? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: Direction = ASC,

    ): DefaultPageRequest()

fun AddressPaginateRequest.toAddressPage() = AddressPage(
    name = name,

    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)