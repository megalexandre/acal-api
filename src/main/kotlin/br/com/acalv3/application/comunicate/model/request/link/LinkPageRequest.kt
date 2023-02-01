package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest

data class LinkPageRequest(

    val name: String? = null,

) : DefaultPageRequest()
