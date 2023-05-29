package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.resources.model.business.InvoiceEntity
import org.springframework.data.jpa.domain.Specification

class InvoiceSpecification(private val invoice: InvoicePage) {

    fun getSpecification(): Specification<InvoiceEntity> =
        Specification<InvoiceEntity> {  _, _, builder ->
            val predicate = builder.conjunction()
            predicate
        }
}

