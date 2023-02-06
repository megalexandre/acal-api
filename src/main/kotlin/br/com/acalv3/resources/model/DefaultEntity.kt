package br.com.acalv3.resources.model

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.LocalDate

open class DefaultEntity (

    @CreatedDate
    @DateTimeFormat(pattern = DATE_FORMAT, iso = DATE_TIME)
    val createdAt: LocalDate? = null,

    @UpdateTimestamp
    @DateTimeFormat(pattern = DATE_FORMAT, iso = DATE_TIME)
    val updatedAt: LocalDate? = null,
)