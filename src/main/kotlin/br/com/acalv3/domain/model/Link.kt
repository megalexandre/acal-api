package br.com.acalv3.domain.model

data class Link(
    val id: String,
    val customer: Customer,
    val place: Place,
    val mailPlace: Place?,
    val group: Group,
    val active: Boolean,
)