package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Category
import java.math.BigDecimal
import java.util.UUID

class Group(
    val id: UUID,
    val value: BigDecimal,
    val categoryValue: BigDecimal,
    val category: Category,
    val name: String,
)