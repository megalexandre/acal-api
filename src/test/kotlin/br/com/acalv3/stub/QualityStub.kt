package br.com.acalv3.stub

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.enumeration.Param.CHLORINE
import br.com.acalv3.domain.enumeration.Param.COLOR
import br.com.acalv3.domain.enumeration.Param.ESCHERICHIA
import br.com.acalv3.domain.enumeration.Param.TOTAL_COLIFORMS
import br.com.acalv3.domain.enumeration.Param.TURBIDITY
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import java.util.UUID
import java.util.UUID.randomUUID

fun qualityStub(
    id: UUID = randomUUID(),
    startedAt: String = "oh hay",
) = Quality(
    id = id,
    startedAt = startedAt,
).also {
    it.gathering = listOf(
        gatheringStub(param = TOTAL_COLIFORMS,quality = it),
        gatheringStub(param = COLOR,quality = it),
        gatheringStub(param = ESCHERICHIA,quality = it),
        gatheringStub(param = CHLORINE,quality = it),
        gatheringStub(param = TURBIDITY,quality = it),
    )
}

fun gatheringStub(
    id: UUID = randomUUID(),
    param: Param = COLOR,
    required: Long = 10,
    analyzed: Long = 10,
    conformity: Long = 10,
    quality: Quality
) = Gathering (
    id = id,
    param = param,
    required = required,
    analyzed = analyzed,
    conformity = conformity,
    quality = quality,
)
