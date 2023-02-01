package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Link
import java.util.*
import javax.validation.constraints.NotBlank

data class LinkUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

    @field:NotBlank(message = "Nome é um campo obrigatório")
    val name: String? = null,

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    val customerEntity: String?

)

fun LinkUpdateRequest.toLink(customer: Customer) = Link(
    id = UUID.fromString(id?: throw RuntimeException("CustomerUpdateRequest id can't be null")),
    name = name?.trim() ?: throw RuntimeException("CustomerUpdateRequest name can't be null"),
    customer = customer,
)