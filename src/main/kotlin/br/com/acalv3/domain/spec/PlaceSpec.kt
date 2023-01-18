package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.PlaceModel
import br.com.acalv3.domain.spec.v3.AbstractSpec
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class PlaceSpec (
	override var model: PlaceModel,
): AbstractSpec<PlaceModel>(model) {

	override fun toPredicate(
		root: Root<PlaceModel>,
		cq: CriteriaQuery<*>,
		cb: CriteriaBuilder
	): Predicate? {
		val predicates: MutableList<Predicate> = ArrayList()

		equalsId(
			id = model.id,
			root = root,
			cb = cb,
			predicates = predicates,
		)

		nameLikeEnds(
			name = model.name,
			root = root,
			cb = cb,
			predicates = predicates,
		)

		createAtDateTime(
			date = model.createdAt,
			root = root,
			cb = cb,
			predicates = predicates,
		)

		lastModifiedAtDateTime(
			date = model.lastModifiedAt,
			root = root,
			cb = cb,
			predicates = predicates,
		)
		if(model.address?.id != null ) {

			with(predicates){
				add(
					cb.equal(
						root.get<Int>("address").get<Int>("id"),
						model.address?.id
					)
				)
			}
		}

		if(model.letter != null && !model.letter.isNullOrBlank()){
			with(predicates){
				add(
					cb.equal(
						root.get<String>("letter"),
						model.letter
					)
				)
			}
		}

		if(model.number != null && !model.number.isNullOrBlank()){
			with(predicates){
				add(
					cb.equal(
						root.get<String>("number"),
						model.number
					)
				)
			}
		}

		return andTogether(predicates, cb)
	}
}
