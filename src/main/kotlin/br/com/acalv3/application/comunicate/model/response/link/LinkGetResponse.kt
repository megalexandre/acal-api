package br.com.acalv3.application.comunicate.model.response.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place

data class LinkGetResponse(
    val id: String,
    val place: Place,
    val customer: Customer,
    val group: Group,
)

fun Link.toLinkGetResponse() = LinkGetResponse(
    id = id,
    place = place,
    customer = customer,
    group = group,
)