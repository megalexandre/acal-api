package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.PlacePage
import br.com.acalv3.resources.model.business.PlaceEntity
import org.springframework.data.jpa.domain.Specification

class PlaceSpecification(private val place: PlacePage) {

    fun getSpecification(): Specification<PlaceEntity> =
        Specification<PlaceEntity> { _, _, builder ->
            val predicate = builder.conjunction()
            predicate
        }

}

