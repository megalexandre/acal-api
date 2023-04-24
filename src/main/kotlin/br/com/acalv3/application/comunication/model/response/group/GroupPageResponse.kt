package br.com.acalv3.application.comunication.model.response.group

import br.com.acalv3.domain.model.Group
import java.math.BigDecimal
import java.util.UUID
import org.springframework.data.domain.Page

class GroupPageResponse(
    val id: UUID,
    val name: String,
    val value: BigDecimal,
    val category: String,
)

fun Group.toGroupPageResponse() = GroupPageResponse(
    id = id,
    name = name,
    value = value,
    category = category.name
)

fun Page<Group>.toGroupPageResponse() = map{ it.toGroupPageResponse() }