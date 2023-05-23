package br.com.acalv3.domain.filter

import br.com.acalv3.domain.model.Address
import java.util.UUID

data class AddressFilter(

    val id: UUID? = null,
    val name: String? = null,
)

fun Address.toAddress() = AddressFilter (
    id = id,
    name = name,
)