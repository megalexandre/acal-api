package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Link
import java.util.*

data class SaveUpdateLinkResponse
    (val id: UUID)

fun Link.toLinkResponse() = SaveUpdateLinkResponse(
    id = id
)