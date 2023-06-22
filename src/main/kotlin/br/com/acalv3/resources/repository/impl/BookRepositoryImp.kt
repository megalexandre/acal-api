package br.com.acalv3.resources.repository.impl

import br.com.acalv3.commons.defaultFormat
import br.com.acalv3.commons.toCurrency
import br.com.acalv3.commons.toLocalDateTimeStartedDefaultFormat
import br.com.acalv3.domain.enumeration.Report.BOOK
import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.page.BookPage
import br.com.acalv3.domain.repository.BookRepository
import br.com.acalv3.resources.model.business.toBook
import br.com.acalv3.resources.model.business.toBookEntity
import br.com.acalv3.resources.model.business.toBookPage
import br.com.acalv3.resources.model.report.DefaultReport
import br.com.acalv3.resources.model.report.toBookReport
import br.com.acalv3.resources.repository.interfaces.BookRepositoryJpa
import br.com.acalv3.resources.repository.specification.BookSpecification
import java.time.LocalDate
import java.time.LocalTime.MAX
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImp(
    private val repository: BookRepositoryJpa,
) : BookRepository {

    override fun findByCreatedAtDateBetween(day: LocalDate): List<Book>? =
       repository.findByCreatedAtBetween(day.atStartOfDay(), day.atTime(MAX))

    override fun report(page: BookPage): DefaultReport  {
        val books = repository.findAll(BookSpecification(page).getSpecification()).toBook()

        return DefaultReport(
            dataList = books.toBookReport(),
            report = BOOK,
            param = createParamReport(page, books)
        )
    }

    private fun createParamReport(page: BookPage, book: List<Book> ): HashMap<String, Any> = hashMapOf(
            "period" to getPeriod(page),
            "created_by" to (page.createdBy ?: "Todos"),
            "created_at" to (page.createdAt?.defaultFormat() ?: "Todos"),
            "value" to (page.value?.toCurrency() ?: "Todos"),
            "type" to (page.type?.translate ?: "Todos"),
            "reason" to (page.reason?.translate ?: "Todos"),
            "total_value" to book.map { it.value }.reduce{ count, number -> count + number }.toCurrency(),
            "current_user" to SecurityContextHolder.getContext().authentication.principal
        )

    private fun getPeriod(page: BookPage): String{
        val started = page.createdAtStarted?.toLocalDateTimeStartedDefaultFormat()
        val finish = page.createdAtFinish?.toLocalDateTimeStartedDefaultFormat()

        if(started == null || finish == null){
            return "todo o periodo"
        }

        return """de: $started até: $finish"""
    }

    override fun getById(id: String): Book = repository.getById(UUID.fromString(id)).toBook()

    override fun findAll(): List<Book> = repository.findAll().toBook()

    override fun save(type: Book): Book =repository.save(type.toBookEntity()).toBook()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun count(): Long = repository.count()

    override fun paginate(page: BookPage): Page<Book>  =
        repository.findAll(
            BookSpecification(page).getSpecification(),
            super.pageable(page)
        ).toBookPage()

    override fun findAll(page: BookPage): List<Book> =
        repository.findAll(
            BookSpecification(page).getSpecification(),
        ).toBook()



}
