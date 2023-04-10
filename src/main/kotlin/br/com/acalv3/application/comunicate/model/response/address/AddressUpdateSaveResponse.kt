package br.com.acalv3.application.comunicate.model.response.address

import br.com.acalv3.domain.model.Address
import java.util.UUID

class SaveUpdateAddressResponse(
    val id: UUID
)

fun Address.toAddressResponse() = SaveUpdateAddressResponse(
    id = id
)