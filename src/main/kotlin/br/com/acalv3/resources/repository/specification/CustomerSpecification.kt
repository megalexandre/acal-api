package br.com.acalv3.resources.repository.specification

import br.com.acalv3.application.comunicate.model.request.CustomerPageRequest
import br.com.acalv3.resources.model.business.CustomerModel
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class CustomerSpecification(val customer: CustomerPageRequest) {

    fun getSpecification(): Specification<CustomerModel> = run{
        Specification<CustomerModel> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }
    }

    private fun getPredicates(predicate: Predicate, root: Root<CustomerModel>, builder: CriteriaBuilder) {
        if(customer.id != null){
            predicate.expressions.add(eqID(root, builder))
        }

        if (customer.name != null) {
            predicate.expressions.add(likeName(root, builder))
        }

        if (customer.document != null) {
            predicate.expressions.add(likeDocument(root, builder))
        }
    }

    private fun eqID(root: Root<CustomerModel>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<UUID>("id"), customer.id)
    private fun likeName(root: Root<CustomerModel>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get("name")),"%${customer.name}%")
    private fun likeDocument(root: Root<CustomerModel>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get("document")),"%${customer.document}%")
}

