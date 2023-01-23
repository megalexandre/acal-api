package br.com.acalv3.application.comunicate.model.request

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.annotations.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class CustomerRequest(

    @field:NotNull
    @field:NotBlank
    val name: String? = null,

    @field:NotNull
    @field:NotBlank
    var document: String? = null,

    var personType: PersonTypeEnum? = null,

    @field:NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    var birthDay: LocalDate? = null,
)

fun CustomerRequest.toCustomer() = Customer(
    name = name!!,
    document = document!!,
    personType = personType!!,
    birthDay = birthDay!!
)