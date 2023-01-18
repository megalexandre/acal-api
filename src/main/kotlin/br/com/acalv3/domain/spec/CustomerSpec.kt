package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.spec.v3.AbstractSpec
import org.testng.util.Strings
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class CustomerSpec (
	override var model: CustomerModel,
): AbstractSpec<CustomerModel>(model){

	override fun toPredicate(
		root: Root<CustomerModel>,
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
		if(model.birthDate != null ) {
			val startingFrom = cb.greaterThanOrEqualTo(
				root.get("birthDate"),
				model.birthDate
			)

			val endingAt =cb.lessThanOrEqualTo(
				root.get("birthDate"),
				model.birthDate
			)

			predicates.add(cb.and(startingFrom, endingAt) )
		}

		if(Strings.isNotNullAndNotEmpty(model.document)){
			with(predicates){
				add(
					cb.like(
						cb.lower(root.get("document")),
						"%" + model.document?.lowercase() + "%"
					)
				)
			}
		}

		return andTogether(predicates, cb)
	}
}
