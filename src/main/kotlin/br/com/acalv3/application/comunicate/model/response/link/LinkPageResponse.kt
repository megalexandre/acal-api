package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Link
import org.springframework.data.domain.Page
import java.util.*

data class LinkPageResponse(
    val id: UUID?,
    val name: String?,
)

fun Link.toLinkPageResponse() = LinkPageResponse(
    id = id,
    name = name,
)
fun Page<Link>.toLinkPageResponse() = map{ it.toLinkPageResponse() }