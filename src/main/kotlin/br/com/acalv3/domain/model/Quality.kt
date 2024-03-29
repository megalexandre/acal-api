package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Param
import java.util.UUID

data class Quality(
    val id: UUID,
    val reference: String,
    var gathering: List<Gathering>?
)

data class Gathering(
    val id: UUID,
    val qualityId: UUID,
    val param: Param,
    val required: Long,
    val analyzed: Long,
    val conformity: Long,
)
