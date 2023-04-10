package br.com.acalv3.application.comunicate.model.request.hydrometer

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.util.UUID

class HydrometerSaveRequest(
    val reference: String,
    val value: Long,
    val totalCounterValue: Long,
    var linkId: UUID?,
)

fun HydrometerSaveRequest.toDomain(link: Link) = Hydrometer(
    id = UUID.randomUUID(),
    reference =  reference,
    value = value,
    totalCounterValue = totalCounterValue,
    link = link,
)