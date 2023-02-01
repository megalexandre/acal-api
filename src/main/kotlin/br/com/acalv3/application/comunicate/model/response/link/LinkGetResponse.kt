package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Link
import java.util.*

data class LinkGetResponse(
    val id: UUID?,
    val name: String?,
)

fun Link.toLinkGetResponse() = LinkGetResponse(
    id = id,
    name = name,
)