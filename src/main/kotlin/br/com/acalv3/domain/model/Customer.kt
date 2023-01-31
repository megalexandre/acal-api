package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import java.time.LocalDate
import java.util.*

class Customer(
    val id: UUID,
    val name: String,
    val document: String,
    val personType: PersonTypeEnum,
    var birthDay: LocalDate? = null,
    val phoneNumber: String?
)