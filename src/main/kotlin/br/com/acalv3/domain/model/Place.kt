package br.com.acalv3.domain.model

class Place(
    val number: Long,
    val id: String,
    val letter: String,
    val address: Address,
    val hasHydrometer: Boolean,
    val other: String?,
)