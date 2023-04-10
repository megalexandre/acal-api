package br.com.acalv3.application.comunicate.model.request.address

import br.com.acalv3.domain.model.Address
import java.util.UUID

class AddressSaveRequest: AddressRequest()

fun AddressSaveRequest.toAddress() = Address(
    id = UUID.randomUUID(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
)