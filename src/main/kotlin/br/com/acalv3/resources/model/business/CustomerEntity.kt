package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.resources.model.DefaultEntity
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import org.springframework.data.domain.Page
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME

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

    @DateTimeFormat(pattern = DATE_FORMAT, iso = DATE_TIME)
    @JsonFormat(pattern = DATE_FORMAT)
    val birthDay: LocalDate? = null,

    ) : DefaultEntity()

fun Customer.toCustomerEntity() = CustomerEntity(
    id = UUID.fromString(id),
    name = name,
    phoneNumber = phoneNumber,
    document = document,
    personType = personType,
    birthDay = birthDay
)

fun CustomerEntity.toCustomer() = Customer(
    id = id.toString(),
    name = name,
    phoneNumber = phoneNumber,
    document = document ?: "",
    personType = personType,
    birthDay = birthDay
)

fun Page<CustomerEntity>.toCustomerPage() = map{ it.toCustomer() }