package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.resources.model.business.QualityEntity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class QualitySpecification(private val qualityPage: QualityPage) {

    fun getSpecification(): Specification<QualityEntity> =
        Specification<QualityEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<QualityEntity>, builder: CriteriaBuilder) {
        with(qualityPage){
            reference?.let {
                predicate.expressions.add(
                    builder.equal(root.get<String>("reference"), reference)
                )
            }
        }
    }
}


