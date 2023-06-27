package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.resources.model.business.CustomerEntity
import br.com.acalv3.resources.model.business.InvoiceEntity
import br.com.acalv3.resources.model.business.LinkEntity
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class InvoiceSpecification(private val invoice: InvoicePage) {

    fun getSpecification(): Specification<InvoiceEntity> =
        Specification<InvoiceEntity> {  root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<InvoiceEntity>, builder: CriteriaBuilder) {

        invoice.reference?.let {
            if(it.isNotEmpty()){
                predicate.expressions.add(eqReference(root, builder))
            }
        }

        invoice.customerName?.let {
            if(it.isNotEmpty()){
                predicate.expressions.add(likeName(root, builder))
            }
        }

        invoice.value?.let {
            if(it != 0)
            predicate.expressions.add(eqValue(root, builder))
        }

        invoice.dueDate?.let {
            predicate.expressions.add(eqDueDate(root,builder))
        }

        invoice.addressId?.let {
            predicate.expressions.add(eqAddress(root,builder))
        }
    }

    private fun eqAddress(root: Root<InvoiceEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(
            root
            .get<Link>("link")
            .get<Place>("place")
            .get<Address>("address")
            .get<UUID>("id"), UUID.fromString(invoice.addressId))

    private fun eqDueDate(root: Root<InvoiceEntity>, builder: CriteriaBuilder): Predicate =
        builder.between(
            root.get<LocalDateTime>("dueDate"),
            invoice.dueDate?.atStartOfDay(),
            invoice.dueDate?.atTime(LocalTime.MAX)
        )


    private fun eqValue(root: Root<InvoiceEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<BigDecimal>("value"), invoice.addressId)

    private fun eqReference(root: Root<InvoiceEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<String>("reference"), invoice.reference)

    private fun likeName(root: Root<InvoiceEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(
            root
                .get<LinkEntity>("link")
                .get<CustomerEntity>("customer")
                .get<String>("name")
        ),"%${invoice.customerName}%")


}

