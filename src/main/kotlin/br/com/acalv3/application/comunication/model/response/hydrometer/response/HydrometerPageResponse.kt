package br.com.acalv3.application.comunication.model.response.hydrometer.response

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.math.BigDecimal
import org.springframework.data.domain.Page

class HydrometerPageResponse(
    val id: String,
    val reference: String,
    val costValue: BigDecimal,
    val consumption: Long,
    val link: Link?,
){
    val linkName = link?.let {"${link.place.address.name}: ${link.place.number} ${link.place.letter}"} ?:""
    val personName = link?.let { link.customer.name }
}

fun Hydrometer.toPageResponse() = HydrometerPageResponse(
    id = id.toString(),
    reference = reference,
    costValue = costValue,
    consumption = consumption,
    link = link,
)

fun Page<Hydrometer>.toPageResponse() = map{ it.toPageResponse() }
