package br.com.acalv3.application.comunicate.model.quality.request

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated

@Validated
class QualityRequest(

    @field:NotNull(message = "os parametros da coleta são obrigatório")
    val gathering: List<GatheringRequest>? ,

    @field:NotNull(message = "Data de inicio é obrigatório")
    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    val startedAt: LocalDate?,

    @field:NotNull(message = "Data final é obrigatório")
    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    val finishedAt: LocalDate?,
)

fun QualityRequest.toQuality() = Quality(
    id = UUID.randomUUID().toString(),
    startedAt = startedAt ?: throw RuntimeException(),
    finishedAt = finishedAt ?: throw RuntimeException(),
).also {
    it.gathering = gathering?.toGathering(it) ?: throw RuntimeException()
}

@Validated
class GatheringRequest(
    @field:NotNull(message = "Um parametro deve ter um nome")
    val param: String?,
    @field:NotNull(message = "Quantidade exigida é obrigatorio")
    val required: Long?,
    @field:NotNull(message = "Quantidade analisado é obrigatorio")
    val analyzed: Long?,
    @field:NotNull(message = "Quantidade Confirmidade é obrigatorio")
    val conformity: Long?,
)

fun GatheringRequest.toGathering(quality: Quality) = Gathering(
    id = UUID.randomUUID().toString(),
    required = required ?: throw RuntimeException(),
    analyzed = analyzed ?: throw RuntimeException(),
    conformity = conformity ?: throw RuntimeException(),
    param = Param.byValue(param) ?: throw RuntimeException("Param $param is not found"),
    quality = quality
)

fun List<GatheringRequest>.toGathering(quality: Quality) = map { it.toGathering(quality) }