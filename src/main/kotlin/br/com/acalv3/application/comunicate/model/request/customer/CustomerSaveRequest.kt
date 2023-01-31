package br.com.acalv3.application.comunicate.model.request.customer

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.application.comunicate.Fixture.Companion.DEFAULT_DATE_TIME_FORMAT
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class CustomerSaveRequest(

    @field:NotBlank(message = "Nome é um campo obrigatório")
    val name: String? = null,

    @field:NotBlank(message = "Documento é um campo obrigatório")
    val document: String? = null,

    @field:NotNull(message = "Tipo da pessoa é um campo obrigatório")
    @field:NotBlank(message = "Tipo da pessoa é um campo obrigatório")
    @field:Pattern(regexp = "PERSON|LEGAL", message = "os valores aceitos para tipo de pessoa são PERSON ou LEGAL")
    val personType: String,

    val phoneNumber: String?,

    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    val birthDay: LocalDate? = null,
)

fun CustomerSaveRequest.toCustomer() = Customer(
    id = UUID.randomUUID(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    document = document?.trim()  ?: throw RuntimeException("document can't be null"),
    phoneNumber = phoneNumber?.trim(),
    personType = PersonTypeEnum.valueOf(personType),
    birthDay = birthDay
)