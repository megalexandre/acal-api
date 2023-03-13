package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import java.time.LocalDateTime
import org.springframework.data.domain.Page

data class LinkPageResponse(
    val id: String,
    val place: Place,
    val customer: Customer,
    val group: Group,
    val active: Boolean,
    val startedAt: LocalDateTime,
    val finishedAt: LocalDateTime?,
)

fun Link.toLinkPageResponse() = LinkPageResponse(
    id = id,
    place = place,
    customer = customer,
    group = group,
    active = active,
    startedAt = startedAt,
    finishedAt = finishedAt,
)
fun Page<Link>.toLinkResponse() = map{ it.toLinkPageResponse() }
fun List<Link>.toLinkResponse() = map{ it.toLinkPageResponse() }