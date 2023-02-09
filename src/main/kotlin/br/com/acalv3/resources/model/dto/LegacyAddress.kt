package br.com.acalv3.resources.model.dto

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Customer
import java.util.*


class LegacyAddress (
    val name: String,
)

fun LegacyAddress.toAddress() = Address(
    id = UUID.randomUUID().toString(),
    name = name,
)
