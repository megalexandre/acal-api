package br.com.acalv3.application.comunicate.model.response.address

import br.com.acalv3.domain.model.Address

data class SaveUpdateAddressResponse(
    val id: String
)

fun Address.toAddressResponse() = SaveUpdateAddressResponse(
    id = id
)