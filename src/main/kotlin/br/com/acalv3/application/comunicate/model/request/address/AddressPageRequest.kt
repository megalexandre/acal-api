package br.com.acalv3.application.comunicate.model.request.address

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class AddressPageRequest(

    val name: String? = null,

): DefaultPageRequest()