package br.com.acalv3.application.comunicate.model.request.group

import java.math.BigDecimal

open class GroupRequest (

    open val name: String? = null,
    open val category: String? = null,
    open val value: BigDecimal? = null,
)