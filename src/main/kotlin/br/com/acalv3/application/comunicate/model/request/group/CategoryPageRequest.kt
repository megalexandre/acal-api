package br.com.acalv3.application.comunicate.model.request.group

import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.GroupPage
import java.math.BigDecimal

class GroupPageRequest(

    val category: String? = null,
    val value: BigDecimal? = null,
    val name: String? = null,

): DefaultPageRequest()

fun GroupPageRequest.toGroupPage() = GroupPage(
    name = name
)