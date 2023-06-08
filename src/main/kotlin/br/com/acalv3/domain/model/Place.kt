package br.com.acalv3.domain.model

import java.util.UUID

class Place(
    val id: UUID,
    val addressId: UUID,
    val address: Address? = null,
    val number: Long,
    val letter: String,
    val hasHydrometer: Boolean,
    val other: String?,
){
    val addressName: String = "${address?.name}, $number$letter"
}