package br.com.acalv3.application.comunicate.model.response.hydrometer.response

import br.com.acalv3.domain.model.Hydrometer
import org.springframework.data.domain.Page

class HydrometerPageResponse(
    val id: String,
    val reference: String,
    val value: Long,
    val totalCounterValue: Long,
)

fun Hydrometer.toPageResponse() = HydrometerPageResponse(
    id = id.toString(),
    reference = reference,
    value = value,
    totalCounterValue = totalCounterValue,
)

fun Page<Hydrometer>.toPageResponse() = map{ it.toPageResponse() }
