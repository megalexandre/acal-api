package br.com.acalv3.domain.model

import java.util.UUID

class Place(
    val id: UUID,
    val number: Long,
    val letter: String,
    val address: Address,
    val hasHydrometer: Boolean,
    val other: String?,
)