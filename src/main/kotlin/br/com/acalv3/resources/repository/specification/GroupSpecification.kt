package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.resources.model.business.GroupEntity
import java.math.BigDecimal
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class GroupSpecification(private val place: GroupPage) {

    fun getSpecification(): Specification<GroupEntity> =
        Specification<GroupEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<GroupEntity>, builder: CriteriaBuilder) {
        if (!place.name.isNullOrEmpty()) {
            predicate.expressions.add(eqName(root, builder))
        }

        if (place.value != null) {
            predicate.expressions.add(eqValue(root, builder))
        }

        if(place.category != null){
            predicate.expressions.add(eqCategory(root, builder))
        }
    }

    private fun eqName(root: Root<GroupEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get("name")),"%${place.name?.trim()}%")

    private fun eqValue(root: Root<GroupEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<BigDecimal>("value"), place.value)

    private fun eqCategory(root: Root<GroupEntity>, builder: CriteriaBuilder): Predicate = run{
        builder.equal(root.get<String>("category"), place.category)
    }


}

