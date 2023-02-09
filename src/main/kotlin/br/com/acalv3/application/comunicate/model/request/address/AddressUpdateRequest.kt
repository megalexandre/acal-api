package br.com.acalv3.application.comunicate.model.request.address

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Customer
import javax.validation.constraints.NotBlank

data class AddressUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

) : AddressRequest()

fun AddressUpdateRequest.toAddress() = Address(
    id = id?: throw RuntimeException("id can't be null"),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
)