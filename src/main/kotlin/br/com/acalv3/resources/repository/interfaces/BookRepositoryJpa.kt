package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.domain.model.Book
import br.com.acalv3.resources.model.business.BookEntity
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BookRepositoryJpa : JpaRepository<BookEntity, UUID>, JpaSpecificationExecutor<BookEntity>{
    fun findByCreatedAtBetween(endDate: LocalDateTime, startDate: LocalDateTime): List<Book>?
}


