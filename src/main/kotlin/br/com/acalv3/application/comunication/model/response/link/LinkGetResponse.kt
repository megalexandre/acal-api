package br.com.acalv3.application.comunication.model.response.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import java.time.LocalDateTime
import java.util.UUID

data class LinkGetResponse(
    val id: UUID,
    val place: Place?,
    val mailPlace: Place?,
    val customer: Customer?,
    val group: Group?,
    val startedAt: LocalDateTime,
    val finishedAt: LocalDateTime?,
    val linkName: String,
    val createdBy: String,
)

fun Link.toLinkGetResponse() = LinkGetResponse(
    id = id,
    place = place,
    customer = customer,
    group = group,
    startedAt = startedAt,
    finishedAt = finishedAt,
    mailPlace = mailPlace,
    linkName = addressName,
    createdBy = createdBy,
)

fun List<Link>.toLinkGetResponse() = map{ it.toLinkGetResponse() }