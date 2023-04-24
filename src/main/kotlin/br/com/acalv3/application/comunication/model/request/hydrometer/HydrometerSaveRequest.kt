package br.com.acalv3.application.comunication.model.request.hydrometer

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.math.BigDecimal
import java.util.UUID

class HydrometerSaveRequest(
    val reference: String,
    val costValue: BigDecimal,
    val actualQuantity: Long,
    val lastMonthQuantity: Long,
    var linkId: UUID?,
){
    val quantity = actualQuantity - lastMonthQuantity
}

fun HydrometerSaveRequest.toDomain(link: Link) = Hydrometer(
    id = UUID.randomUUID(),
    reference =  reference,
    costValue = costValue,
    actualQuantity = actualQuantity,
    lastMonthQuantity = lastMonthQuantity,
    link = link,
)