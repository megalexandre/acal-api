package br.com.acalv3.application.comunication.model.request.link

import br.com.acalv3.domain.model.Link
import java.time.LocalDateTime
import java.util.UUID.fromString
import javax.validation.constraints.NotBlank

data class LinkUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

    @field:NotBlank(message = "Cliente é um campo obrigatório")
    val customerId: String? = null,

    @field:NotBlank(message = "Grupo é um campo obrigatório")
    val groupId: String? = null,

    @field:NotBlank(message = "Endereco é um campo obrigatório")
    val placeId: String? = null,

    @field:NotBlank(message = "Endereco é um campo obrigatório")
    val mailPlaceId: String? = null,

    val active: Boolean,

    val startedAt: LocalDateTime,

    val finishedAt: LocalDateTime,

    val createdBy: String

): LinkRequest()

fun LinkUpdateRequest.toLink() = Link(
    id = fromString(id) ?: throw RuntimeException("CustomerUpdateRequest id can't be null"),
    customerId = fromString(customerId) ?: throw RuntimeException("CustomerUpdateRequest id can't be null"),
    placeId = fromString(placeId) ?: throw RuntimeException("place id can't be null"),
    mailPlaceId = fromString(mailPlaceId) ?: throw RuntimeException("place id can't be null"),
    groupId = fromString(groupId) ?: throw RuntimeException("group id can't be null"),
    createdBy = createdBy,
    active = active,
    startedAt = startedAt,
    finishedAt = finishedAt,
)