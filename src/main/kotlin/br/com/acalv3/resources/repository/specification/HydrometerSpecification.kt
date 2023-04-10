package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.HydrometerPage
import br.com.acalv3.resources.model.business.HydrometerEntity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class HydrometerSpecification(private val hydrometer: HydrometerPage) {

    fun getSpecification(): Specification<HydrometerEntity> =
        Specification<HydrometerEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<HydrometerEntity>, builder: CriteriaBuilder){}

    companion object{
        private const val NAME = "name"
    }
}

