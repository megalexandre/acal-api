package br.com.acalv3.application.comunicate.model.request.quality

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.UUID
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate

class QualityRequest(

    val gathering: List<GatheringRequest>,

    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    val startedAt: LocalDate,

    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    val finishedAt: LocalDate,
)

fun QualityRequest.toQuality() = Quality(
    id = UUID.randomUUID().toString(),
    startedAt = startedAt,
    finishedAt = finishedAt,
).also {
    it.gathering = gathering.toGathering(it)
}

class GatheringRequest(
    val param: String,
    val required: Long,
    val analyzed: Long,
    val conformity: Long,
)

fun GatheringRequest.toGathering(quality: Quality) = Gathering(
    id = UUID.randomUUID().toString(),
    required = required,
    param = param,
    analyzed = analyzed,
    conformity = conformity,
    quality = quality
)

fun List<GatheringRequest>.toGathering(quality: Quality) = map { it.toGathering(quality) }