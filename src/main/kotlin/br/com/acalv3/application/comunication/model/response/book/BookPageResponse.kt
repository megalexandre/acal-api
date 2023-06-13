package br.com.acalv3.application.comunication.model.response.book

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.model.Book
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.domain.Page

data class BookPageResponse(
    val id: UUID,
    val value: BigDecimal,
    val createdBy: String,

    @JsonFormat(pattern = DATE_FORMAT)
    val createdAt: LocalDateTime,
    val type: Type,
    val reason: Reason,
)

fun Book.toPageResponse() = BookPageResponse(
    id = id,
    value = value,
    createdBy = createdBy,
    createdAt = createdAt,
    type = type,
    reason = reason,
)

fun Page<Book>.toPageResponse() = map{ it.toPageResponse() }