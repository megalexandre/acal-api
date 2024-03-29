package br.com.acalv3.application.comunication.model.response.hydrometer.response

import br.com.acalv3.application.comunication.model.response.link.LinkGetResponse
import br.com.acalv3.application.comunication.model.response.link.toLinkGetResponse
import br.com.acalv3.domain.model.Hydrometer
import java.math.BigDecimal

class HydrometerResponse(
    val id: String,
    val reference: String,
    val costValue: BigDecimal,
    val consumption: Long,
    var link: LinkGetResponse?,
){
    val linkName: String? = link?.let {"${link?.place?.address?.name}: ${link?.place?.number} ${link?.place?.letter}"}
    val personName = link?.let { link?.customer?.name }
}

fun Hydrometer.toResponse(): HydrometerResponse = HydrometerResponse(
    id = id.toString(),
    reference = reference,
    costValue = costValue,
    consumption = consumption,
    link = link?.toLinkGetResponse(),
)

