package br.com.acalv3.domain.model

import br.com.acalv3.domain.enumeration.Param
import java.time.LocalDate

class Quality(
    val id: String,
    val startedAt: LocalDate,
    val finishedAt: LocalDate,
) {
    var gathering: List<Gathering>? = null
    fun gathering(): List<Gathering> = gathering ?: throw RuntimeException("gathering cant be null")
}

class Gathering(
    val id: String,
    val param: Param,
    val required: Long,
    val analyzed: Long,
    val conformity: Long,
    var quality: Quality
)