package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.HydrometerPage
import br.com.acalv3.resources.model.business.HydrometerEntity
import org.springframework.data.jpa.domain.Specification

class HydrometerSpecification(private val hydrometer: HydrometerPage) {

    fun getSpecification(): Specification<HydrometerEntity> =
        Specification<HydrometerEntity> { _, _, builder ->
            val predicate = builder.conjunction()
            predicate
        }

    companion object{
        private const val NAME = "name"
    }
}

