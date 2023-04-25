package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.resources.model.business.AddressEntity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class AddressSpecification(private val address: AddressPage) {

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

