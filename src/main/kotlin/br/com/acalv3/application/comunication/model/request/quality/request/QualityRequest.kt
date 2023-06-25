package br.com.acalv3.application.comunication.model.request.quality.request

import br.com.acalv3.application.comunication.Fixture.Companion.REFERENCE_REGEX
import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import java.util.UUID
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import org.springframework.validation.annotation.Validated

@Validated
class QualityRequest(

    @field:NotNull(message = "os parametros da coleta são obrigatório")
    val gathering: List<GatheringRequest>?,

    @field:Pattern(regexp=REFERENCE_REGEX, message="Referência deve ter o formato MMYYYY")
    @field:NotNull(message = "Referência é obrigatório")
    val reference: String?,
)

fun QualityRequest.toQuality(qualityId: UUID) = Quality(
    id = qualityId,
    reference = reference ?: throw RuntimeException(),
    gathering = gathering?.toGathering(qualityId) ?: throw RuntimeException()
)

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

fun GatheringRequest.toGathering(qualityId: UUID) = Gathering(
    id = qualityId,
    qualityId = qualityId,
    required = required ?: throw RuntimeException(),
    analyzed = analyzed ?: throw RuntimeException(),
    conformity = conformity ?: throw RuntimeException(),
    param = Param.byValue(param) ?: throw RuntimeException("Param $param is not found"),
)

fun List<GatheringRequest>.toGathering(qualityId: UUID) = map { it.toGathering(qualityId) }