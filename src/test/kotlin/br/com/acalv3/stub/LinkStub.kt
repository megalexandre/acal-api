package br.com.acalv3.stub

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.UUID

fun linkStub(
    id: UUID = UUID.randomUUID(),
    customer: Customer = customerStub(),
    place: Place = placeStub(),
    mailPlace: Place = placeStub(),
    group: Group = groupStub(),
    active: Boolean = true,
    startedAt: LocalDateTime = now(),
    finishedAt: LocalDateTime? = now()
) = Link(
    id = id,
    customer = customer,
    place = place,
    mailPlace = mailPlace,
    group = group,
    active = active,
    startedAt = startedAt,
    finishedAt = finishedAt,
)