package br.com.acalv3.application.comunicate.model.response.address

import br.com.acalv3.domain.model.Address
import java.util.UUID
import org.springframework.data.domain.Page

class AddressPageResponse(
    val id: UUID?,
    val name: String?,
)

fun Address.toAddressPageResponse() = AddressPageResponse(
    id = id,
    name = name,
)

fun Page<Address>.toAddressPageResponse() = map{ it.toAddressPageResponse() }