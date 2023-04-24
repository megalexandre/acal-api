package br.com.acalv3.application.comunication.model.request.address

import br.com.acalv3.domain.filter.AddressFilter
import br.com.acalv3.domain.model.Address
import java.util.UUID

data class AddressFilterRequest(

    val id: String? = null,
    override val name: String? = null,

) : AddressRequest()

fun AddressFilterRequest.toAddress() = AddressFilter(
    id =  id?.let { UUID.fromString(id) },
    name = name?.trim(),
)

fun Address.toAddress() = AddressFilter (
    id = id,
    name = name,
)