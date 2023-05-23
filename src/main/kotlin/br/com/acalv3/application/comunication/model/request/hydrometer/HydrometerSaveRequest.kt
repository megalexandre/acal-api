package br.com.acalv3.application.comunication.model.request.hydrometer

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.math.BigDecimal
import java.util.UUID

class HydrometerSaveRequest(
    val reference: String,
    val costValue: BigDecimal,
    val consumption: Long,
    var linkId: UUID?,
)

fun HydrometerSaveRequest.toDomain(link: Link) = Hydrometer(
    id = UUID.randomUUID(),
    reference =  reference,
    costValue = costValue,
    consumption = consumption,
    link = link,
)