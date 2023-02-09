package br.com.acalv3.application.comunicate.model.response.address

import br.com.acalv3.domain.model.Address

data class AddressGetResponse(
    val id: String?,
    val name: String?,
)

fun Address.toGetAddressResponse() = AddressGetResponse(
    id = id,
    name = name,
)