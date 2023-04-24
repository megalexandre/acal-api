package br.com.acalv3.application.comunication.model.request.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.util.UUID
import javax.validation.constraints.NotBlank

class LinkSaveRequest (

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    val customerId: String? = null,

    @field:NotBlank(message = "Grupo é um campo obrigatório")
    val groupId: String? = null,

    @field:NotBlank(message = "Grupo é um campo obrigatório")
    val placeId: String? = null,

    @field:NotBlank(message = "Endereço de correpondencia é obrigatorio")
    val mailPlaceId: String? = null,

    ) : LinkRequest(){

    fun customerId(): String = customerId?: throw RuntimeException("customerId cant be null")
    fun groupId(): String = groupId?: throw RuntimeException("groupId cant be null")
    fun placeId(): String = placeId?: throw RuntimeException("placeId cant be null")
    fun mailPlaceId(): String = mailPlaceId?: throw RuntimeException("mailPlaceId cant be null")
}

fun LinkSaveRequest.toLink(
    customer: Customer,
    place: Place,
    mailPlace: Place,
    group: Group,
) = Link(
    id = UUID.randomUUID(),
    customer = customer,
    place = place,
    mailPlace = mailPlace,
    group = group,
    active = true,
    startedAt = LocalDateTime.now(),
)