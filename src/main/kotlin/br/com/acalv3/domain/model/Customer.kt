package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.PersonType
import java.time.LocalDate

class Customer(
    val id: String,
    val name: String,
    val document: String,
    val personType: PersonType,
    var birthDay: LocalDate? = null,
    val phoneNumber: String?
)