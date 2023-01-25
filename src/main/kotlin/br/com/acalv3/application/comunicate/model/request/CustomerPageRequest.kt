package br.com.acalv3.application.comunicate.model.request

import java.util.*

data class CustomerPageRequest(

    val id: UUID? = null,
    val name: String? = null,
    val document: String? = null,

)
