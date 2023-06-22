package br.com.acalv3.application.comunication.model.request.quality.response

import br.com.acalv3.domain.model.Quality
import org.springframework.data.domain.Page

class QualityPageResponse(
    val id: String,
    val reference: String,
    var gathering: List<GatheringResponse>? = null
)

fun Quality.toPageResponse() = QualityPageResponse(
    id = id.toString(),
    reference = reference,
    gathering = gathering?.toGatheringResponse()
)

fun Page<Quality>.toPageResponse() = map{ it.toPageResponse() }