package br.com.acalv3.application.comunication.model.request.customer

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_FORMAT
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

open class CustomerRequest (

    @field:NotBlank(message = "Nome é um campo obrigatório")
    open val name: String? = null,

    @field:NotBlank(message = "Documento é um campo obrigatório")
    open var document: String? = null,

    @field:NotBlank(message = "Tipo da pessoa é um campo obrigatório")
    @field:Pattern(regexp = "PERSON|LEGAL", message = "os valores aceitos para tipo de pessoa são PERSON ou LEGAL")
    open var personType: String? = null,

    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    open var birthDay: LocalDate? = null,

    open val phoneNumber: String? = null,

    open val active: Boolean? = true,

    open val membershipNumber: Int = 0,
)