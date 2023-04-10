package br.com.acalv3.application.comunicate.model.response.group

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.model.Group
import java.math.BigDecimal
import java.util.UUID

class GroupGetResponse(
    val id: UUID,
    val name: String,
    val value: BigDecimal,
    val category: String,
)

fun GroupGetResponse.toGetGroupResponse() = Group(
    id = id,
    name = name,
    value = value,
    category = Category.byValue(category) ?: throw RuntimeException("Category not found"),
)

fun Group.toGetGroupResponse() = GroupGetResponse(
    id = id,
    name = name,
    value = value,
    category = category.name,
)

fun List<Group>.toGroupGetResponse() = map{ it.toGetGroupResponse() }
