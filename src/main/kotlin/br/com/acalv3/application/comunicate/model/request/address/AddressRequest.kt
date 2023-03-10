package br.com.acalv3.application.comunicate.model.request.address

import javax.validation.constraints.NotBlank

open class AddressRequest (

    @field:NotBlank(message = "Nome é um campo obrigatório")
    open val name: String? = null,
)