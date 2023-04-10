package br.com.acalv3.domain.model

import java.util.UUID

class Hydrometer(
    val id: UUID,
    val reference: String,
    val value: Long,
    val totalCounterValue: Long,
    var link: Link?,
)