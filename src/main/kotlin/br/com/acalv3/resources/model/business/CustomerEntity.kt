package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.application.comunicate.Fixture.Companion.DEFAULT_DATE_TIME_FORMAT
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Link
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.domain.Page
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity(name = "customer")
class CustomerEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    val personType: PersonTypeEnum,

    val phoneNumber: String? = null,

    val document: String? = null,

    /*
    @OneToMany(mappedBy = "LinkEntity")
    val links: List<LinkEntity>,
     */

    @DateTimeFormat(pattern = DEFAULT_DATE_TIME_FORMAT, iso = DATE_TIME)
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    val birthDay: LocalDate? = null,
)

fun Customer.toCustomerModel() = CustomerEntity(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    document = document,
    personType = personType,
    birthDay = birthDay
)

fun CustomerEntity.toCustomer() = Customer(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    document = document ?: "",
    personType = personType,
    birthDay = birthDay
)

fun Page<CustomerEntity>.toCustomerPage() = map{ it.toCustomer() }