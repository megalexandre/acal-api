package br.com.acalv3.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Link(
    val id: UUID,

    val group: Group? = null,
    val place: Place? = null,
    val customer: Customer? = null,
    val mailPlace: Place? = null,

    val groupId: UUID,
    val placeId: UUID,
    val customerId: UUID,
    val mailPlaceId: UUID,

    val active: Boolean,
    val startedAt: LocalDateTime,
    val finishedAt: LocalDateTime? = null
) {
    val addressName = place?.address?.name +" "+ place?.number+" "+place?.letter
}