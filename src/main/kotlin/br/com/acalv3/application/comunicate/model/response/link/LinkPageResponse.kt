package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import org.springframework.data.domain.Page

data class LinkPageResponse(
    val id: String,
    val place: Place,
    val customer: Customer,
    val group: Group,
    val active: Boolean,
)

fun Link.toLinkPageResponse() = LinkPageResponse(
    id = id,
    place = place,
    customer = customer,
    group = group,
    active = active,
)
fun Page<Link>.toLinkPageResponse() = map{ it.toLinkPageResponse() }