package br.com.acalv3.resources.repository.specification

import br.com.acalv3.application.comunicate.model.request.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.resources.model.business.CustomerModel
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.domain.Specification.where
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class CustomerSpecification(val customer: CustomerPageRequest) {


    fun getSpecification(): Specification<CustomerModel> = run{
        Specification<CustomerModel> { root, _, builder ->

            val predicate = builder.conjunction()

            if(customer.name != null){
                predicate.expressions.add(nameLikeIfExists(root, builder))
            }

            predicate
        }
    }

    private fun nameLikeIfExists(root: Root<CustomerModel>, builder: CriteriaBuilder): Predicate =
        builder.like(
            builder.upper(root.get("name")),
            "%${customer.name?.trim()}%"
        )

}

