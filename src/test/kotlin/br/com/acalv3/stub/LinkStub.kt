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
    customerId: UUID = customer.id,
    place: Place = placeStub(),
    placeId: UUID = place.id,
    mailPlace: Place = placeStub(),
    mailPlaceId: UUID = mailPlace.id,
    group: Group = groupStub(),
    groupId: UUID = group.id,
    active: Boolean = true,
    startedAt: LocalDateTime = now(),
    finishedAt: LocalDateTime? = now(),
    createdBy: String = "alexandre",
) = Link(
    id = id,
    customer = customer,
    customerId = customerId,
    place = place,
    placeId = placeId,
    mailPlace = mailPlace,
    mailPlaceId = mailPlaceId,
    group = group,
    groupId = groupId,
    active = active,
    createdBy = createdBy,
    startedAt = startedAt,
    finishedAt = finishedAt,
)