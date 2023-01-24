package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity(name = "customer_model")
class CustomerModel (

    @Id
    val id: UUID,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    val personType: PersonTypeEnum,

    var document: String? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    var birthDay: LocalDate? = null,
)

fun Customer.toCustomerModel() = CustomerModel(
    id = id,
    name = name,
    document = document,
    personType = personType,
    birthDay = birthDay
)

fun CustomerModel.toCustomer() = Customer(
    id = id,
    name = name,
    document = document ?: "",
    personType = personType,
    birthDay = birthDay
)
