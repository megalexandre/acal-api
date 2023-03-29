package br.com.acalv3.domain.model

import java.time.LocalDateTime

data class Link(
    val id: String,
    val customer: Customer,
    val place: Place,
    val mailPlace: Place,
    val group: Group,
    val active: Boolean,
    val startedAt: LocalDateTime,
    val finishedAt: LocalDateTime? = null
) {
    val addressName = place.address.name +" "+ place.number+" "+place.letter
}