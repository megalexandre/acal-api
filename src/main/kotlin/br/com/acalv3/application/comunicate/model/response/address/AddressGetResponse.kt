package br.com.acalv3.application.comunicate.model.response.address

import br.com.acalv3.domain.model.Address
import java.util.UUID

class AddressGetResponse(
    val id: UUID,
    val name: String,
)

fun AddressGetResponse.toGetAddress() = Address(
    id = id,
    name = name,
)

fun Address.toGetAddressResponse() = AddressGetResponse(
    id = id,
    name = name,
)

fun List<Address>.toGetAddressResponse() = map{ it.toGetAddressResponse() }
