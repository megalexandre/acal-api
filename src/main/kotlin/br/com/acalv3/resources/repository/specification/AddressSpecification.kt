package br.com.acalv3.resources.repository.specification

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.CustomerEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class AddressSpecification(private val address: AddressPageRequest) {

    fun getSpecification(): Specification<AddressEntity> =
        Specification<AddressEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<AddressEntity>, builder: CriteriaBuilder) {

        if (address.name != null) {
            predicate.expressions.add(likeName(root, builder))
        }
    }

    private fun likeName(root: Root<AddressEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get(NAME)),"%${address.name}%")

    companion object{
        private const val NAME = "name"
    }
}

