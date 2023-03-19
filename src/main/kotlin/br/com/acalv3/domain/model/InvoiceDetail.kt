package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Reason
import java.math.BigDecimal
import java.util.UUID

class InvoiceDetail(
    val id: UUID,
    val reason: Reason,
    val value: BigDecimal,
)