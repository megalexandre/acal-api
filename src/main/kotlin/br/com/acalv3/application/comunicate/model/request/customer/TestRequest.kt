package br.com.acalv3.application.comunicate.model.request.customer

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate

class TestRequest(
    @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy")
    var birthDay: LocalDate? = null,
)
