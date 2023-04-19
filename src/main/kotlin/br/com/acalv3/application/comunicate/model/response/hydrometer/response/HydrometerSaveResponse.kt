package br.com.acalv3.application.comunicate.model.response.hydrometer.response

import br.com.acalv3.domain.model.Hydrometer

class HydrometerSaveResponse(
    val id: String,
)

fun Hydrometer.toSaveResponse(): HydrometerSaveResponse = HydrometerSaveResponse(
    id = id.toString(),
)

