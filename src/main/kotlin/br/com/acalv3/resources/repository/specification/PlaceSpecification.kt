package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.PlacePage
import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.PlaceEntity
import java.util.UUID
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class PlaceSpecification(private val place: PlacePage) {

    fun getSpecification(): Specification<PlaceEntity> =
        Specification<PlaceEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<PlaceEntity>, builder: CriteriaBuilder) {
        if (place.number != null) {
            predicate.expressions.add(eqNumber(root, builder))
        }

        if (!place.letter.isNullOrEmpty()) {
            predicate.expressions.add(eqLetter(root, builder))
        }

        if(place.address != null){
            predicate.expressions.add(eqAddress(root, builder))
        }
    }

    private fun eqNumber(root: Root<PlaceEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<Long>("number"), place.number)

    private fun eqLetter(root: Root<PlaceEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<Long>("letter"), place.letter?.trim())

    private fun eqAddress(root: Root<PlaceEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<AddressEntity>("address").get<UUID>("id"), UUID.fromString(place.address?.id))
}


