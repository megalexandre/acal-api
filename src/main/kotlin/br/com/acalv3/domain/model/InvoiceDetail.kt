package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Reason
import java.math.BigDecimal

class InvoiceDetail(

    val id: String,

    val reason: Reason,

    val value: BigDecimal,

)