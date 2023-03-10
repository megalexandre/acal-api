package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Category
import java.math.BigDecimal

class Group(
    val id: String,
    val value: BigDecimal,
    val category: Category,
    val name: String,
)