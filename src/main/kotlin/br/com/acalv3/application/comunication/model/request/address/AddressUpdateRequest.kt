package br.com.acalv3.application.comunication.model.request.address

import br.com.acalv3.domain.model.Address
import java.util.UUID
import javax.validation.constraints.NotNull


data class AddressUpdateRequest(

    @NotNull
    val id: UUID? = null,

    @NotNull
    override val name: String? = null,

) : AddressRequest()

fun AddressUpdateRequest.toAddress() = Address(
    id = id?: throw RuntimeException("id can't be null"),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
)