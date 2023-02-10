package br.com.acalv3.application.comunicate.model.response.group

import br.com.acalv3.domain.model.Group
import org.springframework.data.domain.Page
import java.math.BigDecimal

class GroupPageResponse(
    val id: String,
    val name: String,
    val value: BigDecimal,
    val category: String,
)

fun Group.toGroupPageResponse() = GroupPageResponse(
    id = id,
    name = name,
    value = value,
    category = category
)

fun Page<Group>.toGroupPageResponse() = map{ it.toGroupPageResponse() }