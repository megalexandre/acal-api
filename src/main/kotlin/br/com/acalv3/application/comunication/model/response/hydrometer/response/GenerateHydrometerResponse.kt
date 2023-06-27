package br.com.acalv3.application.comunication.model.response.hydrometer.response

import br.com.acalv3.domain.dto.GenerateHydrometer
import br.com.acalv3.domain.dto.LinkHydrometerPair

class GenerateHydrometerResponse(
    val addressName: String,
    val linkHydrometerPair: List<LinkHydrometerPair>
)

fun GenerateHydrometer.toGenerateHydrometerResponse() = GenerateHydrometerResponse(
    addressName = addressName,
    linkHydrometerPair = linkHydrometerPair
)

fun List<GenerateHydrometer>.toGenerateHydrometerResponse() = map{ it.toGenerateHydrometerResponse() }