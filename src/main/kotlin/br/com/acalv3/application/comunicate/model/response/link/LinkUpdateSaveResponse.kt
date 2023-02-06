package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Link

data class SaveUpdateLinkResponse(
    val id: String
)

fun Link.toLinkResponse() = SaveUpdateLinkResponse(
    id = id
)