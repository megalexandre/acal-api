package br.com.acalv3.application.comunicate.model.response.group

import br.com.acalv3.domain.model.Group
import java.math.BigDecimal

class GroupGetResponse(
    val id: String,
    val name: String,
    val value: BigDecimal,
    val category: String,
)

fun GroupGetResponse.toGetGroupResponse() = Group(
    id = id,
    name = name,
    value = value,
    category = category,
)

fun Group.toGetGroupResponse() = GroupGetResponse(
    id = id,
    name = name,
    value = value,
    category = category,
)

fun List<Group>.toGroupGetResponse() = map{ it.toGetGroupResponse() }
