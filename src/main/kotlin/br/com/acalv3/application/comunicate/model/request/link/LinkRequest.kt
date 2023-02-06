package br.com.acalv3.application.comunicate.model.request.link

import javax.validation.constraints.NotBlank

open class LinkRequest (

    @field:NotBlank(message = "Nome é um campo obrigatório")
    open val name: String? = null,

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    open val customer: String? = null

)