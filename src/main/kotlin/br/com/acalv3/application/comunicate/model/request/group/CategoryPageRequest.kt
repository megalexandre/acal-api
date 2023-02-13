package br.com.acalv3.application.comunicate.model.request.group

import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.GroupPage
import java.math.BigDecimal

class GroupPageRequest(

    val category: String? = null,
    val value: BigDecimal? = null,
    val name: String? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",

): DefaultPageRequest()

fun GroupPageRequest.toGroupPage() = GroupPage(
    name = name,
    category = category,
    value =  value,
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)