package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.PersonType
import java.time.LocalDate
import java.util.UUID

class Customer(
    val id: UUID,
    val name: String,
    val document: String,
    val personType: PersonType,
    var birthDay: LocalDate? = null,
    val membershipNumber: Int,
    val phoneNumber: String?,
    val active: Boolean
)