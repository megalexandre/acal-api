package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.application.comunicate.model.request.customer.CustomerUpdateRequest
import br.com.acalv3.application.comunicate.model.request.group.GroupUpdateRequest
import br.com.acalv3.application.comunicate.model.request.group.toGroup
import br.com.acalv3.application.comunicate.model.request.place.PlaceUpdateRequest
import br.com.acalv3.application.comunicate.model.request.place.toPlace
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Link
import javax.validation.constraints.NotBlank

data class LinkUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    val customer: CustomerUpdateRequest? = null,

    @field:NotBlank(message = "Grupo é um campo obrigatório")
    val group: GroupUpdateRequest? = null,

    @field:NotBlank(message = "Endereco é um campo obrigatório")
    val place: PlaceUpdateRequest? = null,

    val placeAddress: PlaceUpdateRequest? = null,

): LinkRequest()

fun LinkUpdateRequest.toLink(customer: Customer) = Link(
    id = id?: throw RuntimeException("CustomerUpdateRequest id can't be null"),
    customer = customer,
    place = place?.toPlace() ?: throw RuntimeException("place id can't be null"),
    placeAddress = placeAddress?.toPlace(),
    group = group?.toGroup() ?: throw RuntimeException("group id can't be null"),
)