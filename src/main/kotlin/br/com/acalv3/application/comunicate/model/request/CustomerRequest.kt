package br.com.acalv3.application.comunicate.model.request

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class CustomerRequest(

    @field:NotBlank(message = "Nome é um campo obrigatório")
    val name: String? = null,

    @field:NotBlank(message = "Documento é um campo obrigatório")
    var document: String? = null,

    @field:NotNull(message = "Tipo da pessoa é um campo obrigatório")
    @field:NotBlank(message = "Tipo da pessoa é um campo obrigatório")
    @field:Pattern(regexp="PERSON|LEGAL", message = "os valores aceitos para tipo de pessoa são PERSON ou LEGAL")
    var personType: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    var birthDay: LocalDate? = null,
)

fun CustomerRequest.toCustomer() = Customer(
    name = name!!,
    document = document!!,
    personType = PersonTypeEnum.valueOf(personType),
    birthDay = birthDay
)