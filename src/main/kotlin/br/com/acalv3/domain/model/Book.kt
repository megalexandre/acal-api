package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.enumeration.Type
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class Book(
    val id: UUID,
    val value: BigDecimal,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val type: Type,
    val reason: Reason,
)


