package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.page.BookPage
import java.time.LocalDate
import org.springframework.data.domain.Page

interface BookService {

    fun transactionsByDay(day: LocalDate): List<Book>?

    fun paginate(bookPageRequest: BookPage): Page<Book>

    fun report(pageRequest: BookPage): ByteArray?

}
