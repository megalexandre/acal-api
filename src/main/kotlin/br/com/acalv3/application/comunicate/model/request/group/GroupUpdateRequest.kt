package br.com.acalv3.application.comunicate.model.request.group

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.model.Group
import java.util.UUID
import javax.validation.constraints.NotBlank

class GroupUpdateRequest(

    @field:NotBlank(message = "id é um campo obrigatório")
    val id: String? = null,

) : GroupRequest()

fun GroupUpdateRequest.toGroup() = Group(
    id = UUID.fromString(id)?: throw RuntimeException("id can't be null"),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    value =  value ?: throw RuntimeException("name can't be null"),
    category = Category.byValue(category) ?: throw RuntimeException("category can't be null"),
)