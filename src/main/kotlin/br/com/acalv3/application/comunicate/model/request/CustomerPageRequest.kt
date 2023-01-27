package br.com.acalv3.application.comunicate.model.request

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate
import java.util.*

data class CustomerPageRequest(

    val id: UUID? = null,
    val name: String? = null,
    val document: String? = null,
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    val birthDay: LocalDate? = null,
)
