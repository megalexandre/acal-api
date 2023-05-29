package br.com.acalv3.application.comunication.model.request.hydrometer

import br.com.acalv3.domain.model.Hydrometer
import java.math.BigDecimal
import java.util.UUID

class HydrometerSaveRequest(
    val reference: String,
    val costValue: BigDecimal,
    val consumption: Long,
    val link: Link,
)

class Link(
    val id: UUID
)

fun HydrometerSaveRequest.toDomain() = Hydrometer(
    id = UUID.randomUUID(),
    reference =  reference,
    costValue = costValue,
    consumption = consumption,
    linkId = link.id,
    link = null,
)