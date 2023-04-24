package br.com.acalv3.application.comunication.model.response.hydrometer.response

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.math.BigDecimal
import org.springframework.data.domain.Page

class HydrometerPageResponse(
    val id: String,
    val reference: String,
    val costValue: BigDecimal,
    val actualQuantity: Long,
    val lastMonthQuantity: Long,
    val link: Link?,
){
    val linkName = link?.let {"${link.place.address.name}: ${link.place.number} ${link.place.letter}"} ?:""
    val personName = link?.let { link.customer.name }
    val delivery =
        when {
            (actualQuantity - lastMonthQuantity) < 1 -> (actualQuantity - lastMonthQuantity)* -1
            else -> (actualQuantity - lastMonthQuantity)
        }
}

fun Hydrometer.toPageResponse() = HydrometerPageResponse(
    id = id.toString(),
    reference = reference,
    costValue = costValue,
    actualQuantity = actualQuantity,
    lastMonthQuantity = lastMonthQuantity,
    link = link,
)

fun Page<Hydrometer>.toPageResponse() = map{ it.toPageResponse() }
