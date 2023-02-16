package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import java.util.UUID
import javax.validation.constraints.NotBlank

class LinkSaveRequest (

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    val customerId: String? = null,

    @field:NotBlank(message = "Grupo é um campo obrigatório")
    val groupId: String? = null,

    @field:NotBlank(message = "Grupo é um campo obrigatório")
    val placeId: String? = null,

    val placeAddressId: String? = null,

) : LinkRequest()

fun LinkSaveRequest.toLink(
    customer: Customer,
    place: Place,
    placeAddress: Place?,
    group: Group,
) = Link(
    id = UUID.randomUUID().toString(),
    customer = customer,
    place = place,
    placeAddress = place,
    group = group,
)