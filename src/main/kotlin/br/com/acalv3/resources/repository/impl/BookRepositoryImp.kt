package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.page.BookPage
import br.com.acalv3.domain.repository.BookRepository
import br.com.acalv3.resources.model.business.toBook
import br.com.acalv3.resources.model.business.toBookEntity
import br.com.acalv3.resources.model.business.toBookPage
import br.com.acalv3.resources.repository.interfaces.BookRepositoryJpa
import br.com.acalv3.resources.repository.specification.BookSpecification
import java.time.LocalDate
import java.time.LocalTime.MAX
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImp(
    private val repository: BookRepositoryJpa,
) : BookRepository {

    override fun findByCreatedAtDateBetween(day: LocalDate): List<Book>? =
       repository.findByCreatedAtBetween(day.atStartOfDay(), day.atTime(MAX))

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
