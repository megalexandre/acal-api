package br.com.acalv3.application.comunicate.model.quality.response

import br.com.acalv3.domain.model.Quality
import java.time.LocalDate
import org.springframework.data.domain.Page

class QualityPageResponse(
    val id: String,
    val startedAt: String,
    var gathering: List<GatheringResponse>? = null
)

fun Quality.toPageResponse() = QualityPageResponse(
    id = id,
    startedAt = startedAt,
    gathering = gathering().toGatheringResponse()
)

fun Page<Quality>.toPageResponse() = map{ it.toPageResponse() }