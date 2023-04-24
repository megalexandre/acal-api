package br.com.acalv3.application.comunication.model.request.address

import br.com.acalv3.domain.model.Address
import java.util.UUID
import javax.validation.constraints.NotBlank

data class AddressUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: UUID? = null,

) : AddressRequest()

fun AddressUpdateRequest.toAddress() = Address(
    id = id?: throw RuntimeException("id can't be null"),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
)