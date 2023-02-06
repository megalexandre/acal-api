package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Link

data class LinkGetResponse(
    val id: String,
    val name: String,
)

fun Link.toLinkGetResponse() = LinkGetResponse(
    id = id,
    name = name,
)