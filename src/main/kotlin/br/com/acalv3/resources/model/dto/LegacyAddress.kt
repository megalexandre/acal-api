package br.com.acalv3.resources.model.dto

import br.com.acalv3.domain.model.Address
import java.util.UUID


class LegacyAddress (
    val name: String,
)

fun LegacyAddress.toAddress() = Address(
    id = UUID.randomUUID(),
    name = name,
)
