package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.LinkPage

data class LinkPageRequest(

    val name: String? = null,

) : DefaultPageRequest()

fun LinkPageRequest.toPageRequest() = LinkPage(
    name = name
)