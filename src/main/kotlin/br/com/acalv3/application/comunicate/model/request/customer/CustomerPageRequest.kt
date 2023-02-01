package br.com.acalv3.application.comunicate.model.request.customer

import br.com.acalv3.application.comunicate.Fixture.Companion.DEFAULT_DATE_TIME_FORMAT
import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class CustomerPageRequest(

    val name: String? = null,
    val document: String? = null,
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    val birthDay: LocalDate? = null,

): DefaultPageRequest()