package br.com.acalv3.application.comunicate.model.request.customer

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

    open var birthDay: LocalDate? = null,

    open val phoneNumber: String? = null,

)