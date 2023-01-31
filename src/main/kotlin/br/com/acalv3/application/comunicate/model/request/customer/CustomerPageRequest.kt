package br.com.acalv3.application.comunicate.model.request.customer

import br.com.acalv3.application.comunicate.Fixture.Companion.DEFAULT_DATE_TIME_FORMAT
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate

data class CustomerPageRequest(

    val name: String? = null,
    val document: String? = null,
    val page: Int?,
    val pageSize: Int?,
    val direction: String?,
    val sortedField: String?,
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    val birthDay: LocalDate? = null,

)