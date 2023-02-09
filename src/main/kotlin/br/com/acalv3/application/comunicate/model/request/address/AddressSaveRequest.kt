package br.com.acalv3.application.comunicate.model.request.address

import br.com.acalv3.domain.model.Address
import java.util.*

class AddressSaveRequest: AddressRequest()

fun AddressSaveRequest.toAddress() = Address(
    id = UUID.randomUUID().toString(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
)