package br.com.acalv3.application.comunication.model.request.group

import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.model.page.GroupPage
import java.math.BigDecimal

class GroupPageRequest(

    val category: String? = null,
    val value: BigDecimal? = null,
    val name: String? = null,
    val categoryValue: BigDecimal? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",

): DefaultPageRequest()

fun GroupPageRequest.toGroupPage() = GroupPage(
    name = name,
    category =  Category.byValue(category),
    value =  value,
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
    categoryValue = categoryValue,
)