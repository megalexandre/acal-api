package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.page.BookPage
import br.com.acalv3.commons.DefaultReport
import java.time.LocalDate

interface BookRepository: AbstractRepository<Book, BookPage> {
    fun findByCreatedAtDateBetween(day: LocalDate): List<Book>?
    fun report(page: BookPage): DefaultReport
}