package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.model.page.BookPage
import br.com.acalv3.resources.model.business.BookEntity
import java.math.BigDecimal
import java.time.LocalTime.MAX
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class BookSpecification(private val book: BookPage) {

    fun getSpecification(): Specification<BookEntity> =
        Specification<BookEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<BookEntity>, builder: CriteriaBuilder) {

        book.value?.let {
            predicate.expressions.add(eqValue(root, builder))
        }
        book.type?.let {
            predicate.expressions.add(eqType(root, builder))
        }
        book.reason?.let {
            predicate.expressions.add(eqReason(root, builder))
        }
        book.createdBy?.let{
            predicate.expressions.add(likeName(root, builder))
        }

        book.createdAt?.let {
            predicate.expressions.add(eqCreatedAt(root, builder))
        }

        if(book.createdAtFinish != null && book.createdAtStarted != null){
            predicate.expressions.add(eqCreateBetween(root, builder))
        }

    }

    private fun eqCreateBetween(root: Root<BookEntity>, builder: CriteriaBuilder): Predicate =
        builder.between(
            root["createdAt"],
            book.createdAtStarted?.atStartOfDay(),
            book.createdAtFinish?.atTime(MAX)
        )


    private fun eqCreatedAt(root: Root<BookEntity>, builder: CriteriaBuilder): Predicate =
        builder.between(
            root["createdAt"],
            book.createdAt?.toLocalDate()?.atStartOfDay(),
            book.createdAt?.toLocalDate()?.atTime(MAX)
        )

    private fun likeName(root: Root<BookEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root["createdBy"]),"%${book.createdBy?.trim()}%")

    private fun eqType(root: Root<BookEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<Type>("type"), book.type)

    private fun eqReason(root: Root<BookEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<Reason>("reason"), book.reason)

    private fun eqValue(root: Root<BookEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<BigDecimal>("value"), book.value)


}

