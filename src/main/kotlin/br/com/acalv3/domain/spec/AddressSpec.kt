package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.spec.v3.AbstractSpec
import org.testng.util.Strings
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class AddressSpec(
	override val model: AddressModel,
): AbstractSpec<AddressModel>(model) {

	override fun toPredicate(
		root: Root<AddressModel>,
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

		if(model.addressType?.id != null ) {
			with(predicates){
					add(
						cb.equal(
							root.get<Int>("addressType").get<Int>("id"),
							model.addressType?.id
					)
				)
			}
		}

		if(Strings.isNotNullAndNotEmpty(model.addressType?.name) ) {
			with(predicates){
				add(
					cb.equal(
						root.get<String>("addressType").get<Int>("name"),
						model.addressType?.name
					)
				)
			}
		}

		return andTogether(predicates, cb)
	}



}