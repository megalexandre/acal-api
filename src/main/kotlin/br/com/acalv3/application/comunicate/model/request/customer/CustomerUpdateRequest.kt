package br.com.acalv3.application.comunicate.model.request.customer

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.application.comunicate.Fixture.Companion.DEFAULT_DATE_TIME_FORMAT
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class CustomerUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

    @field:NotBlank(message = "Nome é um campo obrigatório")
    val name: String? = null,

    @field:NotBlank(message = "Documento é um campo obrigatório")
    var document: String? = null,

    val phoneNumber: String?,

    @field:NotNull(message = "Tipo da pessoa é um campo obrigatório")
    @field:NotBlank(message = "Tipo da pessoa é um campo obrigatório")
    @field:Pattern(regexp = "PERSON|LEGAL", message = "os valores aceitos para tipo de pessoa são PERSON ou LEGAL")
    var personType: String?,

    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    var birthDay: LocalDate? = null,
)

fun CustomerUpdateRequest.toCustomer() = Customer(
    id = UUID.fromString(id?: throw RuntimeException("CustomerUpdateRequest id can't be null")),
    name = name?.trim() ?: throw RuntimeException("CustomerUpdateRequest name can't be null"),
    document = document?.trim() ?: throw RuntimeException("CustomerUpdateRequest document can't be null"),
    personType = PersonTypeEnum.valueOf(
        personType ?: throw RuntimeException("CustomerUpdateRequest person type can't be null")),
    phoneNumber = phoneNumber?.trim(),
    birthDay = birthDay
)