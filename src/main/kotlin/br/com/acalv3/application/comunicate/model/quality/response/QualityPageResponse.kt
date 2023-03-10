package br.com.acalv3.application.comunicate.model.quality.response

import br.com.acalv3.domain.model.Quality
import org.springframework.data.domain.Page

class QualityPageResponse(
    val id: String?,
)

fun Quality.toPageResponse() = QualityPageResponse(
    id = id,
)

fun Page<Quality>.toPageResponse() = map{ it.toPageResponse() }