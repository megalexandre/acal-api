package br.com.acalv3.domain.model

import java.math.BigDecimal
import java.util.UUID

class Hydrometer(

    val id: UUID,
    val reference: String,

    val costValue: BigDecimal,
    val actualQuantity: Long,
    val lastMonthQuantity: Long,

    var link: Link?,
)