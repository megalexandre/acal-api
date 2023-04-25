package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.resources.model.business.InvoiceEntity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class InvoiceSpecification(private val invoice: InvoicePage) {

    fun getSpecification(): Specification<InvoiceEntity> =
        Specification<InvoiceEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<InvoiceEntity>, builder: CriteriaBuilder) {

    }

}

