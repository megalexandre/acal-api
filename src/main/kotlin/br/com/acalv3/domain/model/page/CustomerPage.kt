package br.com.acalv3.domain.model.page

import java.time.LocalDate

data class CustomerPage (
    val name: String?,
    val document: String?,
    val birthDay: LocalDate? = null,
) : DefaultPage()
