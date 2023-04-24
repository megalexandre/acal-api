package br.com.acalv3.application.comunication.model.request.quality.request

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import java.util.UUID
import javax.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated

@Validated
class QualityRequest(

    @field:NotNull(message = "os parametros da coleta são obrigatório")
    val gathering: List<GatheringRequest>?,

    @field:NotNull(message = "Data de inicio é obrigatório")
    val startedAt: String?,
)

fun QualityRequest.toQuality() = Quality(
    id = UUID.randomUUID().toString(),
    startedAt = startedAt ?: throw RuntimeException(),
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