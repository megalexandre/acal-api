package br.com.acalv3.domain.model

class Link(
    val id: String,
    val customer: Customer,
    val place: Place,
    val placeAddress: Place?,
    val group: Group,
)