package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Link
import java.util.*
import javax.validation.constraints.NotBlank

data class LinkSaveRequest(

    @field:NotBlank(message = "Nome é um campo obrigatório")
    val name: String?,

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    val customer: String?

)

fun LinkSaveRequest.toLink(customer: Customer) = Link(
    id = UUID.randomUUID(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    customer = customer,
)