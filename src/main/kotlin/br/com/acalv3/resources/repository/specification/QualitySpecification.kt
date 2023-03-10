package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.resources.model.business.QualityEntity
import org.springframework.data.jpa.domain.Specification

class QualitySpecification(private val qualityPage: QualityPage) {

    fun getSpecification(): Specification<QualityEntity> =
        Specification<QualityEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            predicate
        }

}


