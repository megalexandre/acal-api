package br.com.acalv3.application.comunicate.model.request.address

import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.AddressPage

data class AddressPageRequest(

    val name: String? = null,

): DefaultPageRequest()

fun AddressPageRequest.toAddressPage() = AddressPage(
    name = name,
)