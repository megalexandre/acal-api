package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.GroupModel
import br.com.acalv3.domain.spec.v3.AbstractSpec
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class GroupSpec(
	override val model: GroupModel,
): AbstractSpec<GroupModel>(model) {

	override fun toPredicate(
		root: Root<GroupModel>,
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
		if(model.category != null ) {

			with(predicates){
				add(
					cb.equal(
						root.get<Int>("category"), model.category
					)
				)
			}
		}

		if(model.monetaryValue != null ) {

			with(predicates){
				add(
					cb.equal(
						root.get<Double>("monetaryValue"), model.monetaryValue
					)
				)
			}
		}

		return andTogether(predicates, cb)
	}

}