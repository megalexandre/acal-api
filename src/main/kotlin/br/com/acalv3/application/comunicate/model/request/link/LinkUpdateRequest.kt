package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Link
import javax.validation.constraints.NotBlank

data class LinkUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

): LinkRequest()

fun LinkUpdateRequest.toLink(customer: Customer) = Link(
    id = id?: throw RuntimeException("CustomerUpdateRequest id can't be null"),
    name = name?.trim() ?: throw RuntimeException("CustomerUpdateRequest name can't be null"),
    customer = customer,
)