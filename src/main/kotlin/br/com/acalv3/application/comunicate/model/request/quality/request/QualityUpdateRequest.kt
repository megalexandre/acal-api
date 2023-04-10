package br.com.acalv3.application.comunicate.model.request.quality.request

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import java.util.UUID
import javax.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated

@Validated
class QualityUpdateRequest(

    @field:NotNull
    val id: String?,

    @field:NotNull(message = "os parametros da coleta são obrigatório")
    val gathering: List<GatheringUpdateRequest>?,

    @field:NotNull(message = "Data de inicio é obrigatório")
    val startedAt: String?,
)

fun QualityUpdateRequest.toQuality() = Quality(
    id = UUID.fromString(id).toString(),
    startedAt = startedAt ?: throw RuntimeException(),
).also {
    it.gathering = gathering?.toGathering(it) ?: throw RuntimeException()
}

@Validated
class GatheringUpdateRequest(
    @field:NotNull
    val id: String?,
    @field:NotNull(message = "Um parametro deve ter um nome")
    val param: String?,
    @field:NotNull(message = "Quantidade exigida é obrigatorio")
    val required: Long?,
    @field:NotNull(message = "Quantidade analisado é obrigatorio")
    val analyzed: Long?,
    @field:NotNull(message = "Quantidade Confirmidade é obrigatorio")
    val conformity: Long?,
)

fun GatheringUpdateRequest.toGathering(quality: Quality) = Gathering(
    id = UUID.fromString(id).toString(),
    required = required ?: throw RuntimeException(),
    analyzed = analyzed ?: throw RuntimeException(),
    conformity = conformity ?: throw RuntimeException(),
    param = Param.byValue(param) ?: throw RuntimeException("Param $param is not found"),
    quality = quality
)

fun List<GatheringUpdateRequest>.toGathering(quality: Quality) = map { it.toGathering(quality) }