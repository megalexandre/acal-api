package br.com.acalv3.application.comunicate.model.quality.response

import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import java.time.LocalDate

class QualityResponse(
    val id:  String,
    val startedAt: LocalDate,
    val finishedAt: LocalDate,
    val gathering: List<GatheringResponse>,
)

class GatheringResponse(
    val id: String,
    val param: String,
    val required: Long,
    val analyzed: Long,
    val conformity: Long,
)

fun Quality.toQualityResponse() = QualityResponse(
    id = id,
    startedAt = startedAt,
    finishedAt = finishedAt,
    gathering = gathering().toGatheringResponse(),
)

fun Gathering.toGatheringResponse() = GatheringResponse(
    id = id,
    required = required,
    param = param.value,
    analyzed = analyzed,
    conformity = conformity,
)

fun List<Gathering>.toGatheringResponse() = map { it.toGatheringResponse() }
fun List<Quality>.toQualityResponse() = map { it.toQualityResponse() }

