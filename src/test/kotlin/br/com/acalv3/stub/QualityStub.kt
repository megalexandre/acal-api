package br.com.acalv3.stub

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.enumeration.Param.COLOR
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import java.util.UUID
import java.util.UUID.randomUUID

fun qualityStub(
    id: UUID = randomUUID(),
    startedAt: String = "oh hay",
) = Quality(
    id = id,
    reference = startedAt,
    gathering = listOf(gatheringStub()),
)


fun gatheringStub(
    id: UUID = randomUUID(),
    qualityId: UUID = randomUUID(),
    param: Param = COLOR,
    required: Long = 10,
    analyzed: Long = 10,
    conformity: Long = 10,
) = Gathering (
    id = id,
    param = param,
    required = required,
    analyzed = analyzed,
    conformity = conformity,
    qualityId = qualityId,
)
