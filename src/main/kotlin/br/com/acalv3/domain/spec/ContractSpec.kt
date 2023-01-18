package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.spec.v3.AbstractSpec
import org.testng.util.Strings
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class ContractSpec (
	override var model: ContractModel,
): AbstractSpec<ContractModel>(model){

	override fun toPredicate(
		root: Root<ContractModel>,
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

			if (Strings.isNotNullAndNotEmpty(model.customer?.name)) {
				with(predicates) {
					add(
						cb.like(
							cb.lower(root.get<Int>("customer").get("name")),
							"%" + model.customer?.name?.lowercase() + "%"
						)
					)
				}
			}

			if (Strings.isNotNullAndNotEmpty(model.customer?.document)) {
				with(predicates) {
					add(
						cb.like(
							root.get<Int>("customer").get("document"),
							model.customer?.document
						)
					)
				}
			}

			if(model.group?.monetaryValue !== null){
				with(predicates) {
					add(
						cb.equal(
							root.get<Int>("group").get<Double>("monetaryValue"),
							model.group?.monetaryValue
						)
					)
				}
			}

			if (model.placeResidence !== null ) {

				if (model.placeResidence?.address?.id !== null ) {
					with(predicates) {
						add(
							cb.equal(
								root.get<Int>("placeResidence").get<Int>("address").get<Int>("id"),
								model.placeResidence?.address?.id,
							)
						)
					}
				}

				if(Strings.isNotNullAndNotEmpty(model.placeResidence?.number)) {
					with(predicates) {
						add(
							cb.equal(
								root.get<Int>("placeResidence").get<String>("number"),
								model.placeResidence?.number,
							)
						)
					}
				}

				if(Strings.isNotNullAndNotEmpty(model.placeResidence?.letter)) {
					with(predicates) {
						add(
							cb.equal(
								root.get<Int>("placeResidence").get<String>("letter"),
								model.placeResidence?.letter,
							)
						)
					}
				}


			}

		return andTogether(predicates, cb)
	}

}