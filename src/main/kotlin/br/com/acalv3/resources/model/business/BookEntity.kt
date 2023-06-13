package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.model.Book
import br.com.acalv3.resources.model.DefaultEntity
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Id
import org.springframework.data.domain.Page
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME

@Entity(name = "book")
class BookEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(nullable = false)
    val value: BigDecimal,

    @Column(nullable = false, length = 256)
    val createdBy: String,

    @Column(nullable = false)
    @DateTimeFormat(pattern = DATE_TIME_FORMAT, iso = DATE_TIME)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    val createdAt: LocalDateTime,

    @Enumerated(STRING)
    val type: Type,

    @Enumerated(STRING)
    val reason: Reason,

    ) : DefaultEntity()

fun Book.toBookEntity() = BookEntity(
    id = id,
    value = value,
    createdBy = createdBy,
    createdAt = createdAt,
    type = type,
    reason = reason,
)

fun BookEntity.toBook() = Book(
    id = id,
    value = value,
    createdBy = createdBy,
    createdAt = createdAt,
    type = type,
    reason = reason,
)

fun Page<BookEntity>.toBookPage(): Page<Book> = map{ it.toBook() }
fun List<BookEntity>.toBook(): List<Book> = map{ it.toBook() }