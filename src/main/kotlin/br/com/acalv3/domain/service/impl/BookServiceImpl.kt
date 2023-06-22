package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.page.BookPage
import br.com.acalv3.domain.repository.BookRepository
import br.com.acalv3.domain.service.BookService
import br.com.acalv3.domain.service.ReportService
import br.com.acalv3.domain.service.event.BookEvent
import java.time.LocalDate
import org.springframework.context.ApplicationListener
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
	private val bookRepository: BookRepository,
	private val reportService: ReportService,
): BookService, ApplicationListener<BookEvent> {

	override fun onApplicationEvent(event: BookEvent) {
		bookRepository.save(event.book)
	}

	override fun transactionsByDay(day: LocalDate): List<Book>? =
		bookRepository.findByCreatedAtDateBetween(day)

	override fun paginate(bookPageRequest: BookPage): Page<Book> =
		bookRepository.paginate(bookPageRequest)

	override fun report(pageRequest: BookPage): ByteArray? =
		reportService.print(bookRepository.report(pageRequest))

}