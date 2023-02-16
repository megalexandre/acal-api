package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.resources.model.business.InvoiceEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class InvoiceSpecification(private val invoice: InvoicePage) {

    fun getSpecification(): Specification<InvoiceEntity> =
        Specification<InvoiceEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<InvoiceEntity>, builder: CriteriaBuilder) {

        if (invoice.name != null) {
            predicate.expressions.add(likeName(root, builder))
        }
    }

    private fun likeName(root: Root<InvoiceEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get(NAME)),"%${invoice.name}%")

    companion object{
        private const val NAME = "name"
    }
}

