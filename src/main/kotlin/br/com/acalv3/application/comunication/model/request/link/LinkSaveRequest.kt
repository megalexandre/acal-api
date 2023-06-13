package br.com.acalv3.application.comunication.model.request.link

import br.com.acalv3.domain.model.Link
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

    val createdBy: String

    ) : LinkRequest(){

    fun groupId(): String = groupId?: throw RuntimeException("groupId cant be null")
    fun placeId(): String = placeId?: throw RuntimeException("placeId cant be null")
    fun mailPlaceId(): String = mailPlaceId?: throw RuntimeException("mailPlaceId cant be null")
}


fun LinkSaveRequest.toLink() = Link (
    id = UUID.randomUUID(),
    customerId = UUID.fromString(customerId),
    placeId = UUID.fromString(placeId),
    mailPlaceId = UUID.fromString(mailPlaceId),
    groupId = UUID.fromString(groupId),
    active = true,
    startedAt = LocalDateTime.now(),
    createdBy = createdBy,
)