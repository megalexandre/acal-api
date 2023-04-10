package br.com.acalv3.stub

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.UUID

fun addressStub(
    id: UUID = UUID.randomUUID(),
    name: String = "oh hay",
) = Address(
    id = id,
    name = name,
)