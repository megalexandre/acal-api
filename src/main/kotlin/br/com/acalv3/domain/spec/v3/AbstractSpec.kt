package br.com.acalv3.domain.spec.v3

import br.com.acalv3.domain.model.AbstractNamedModel
import org.springframework.data.jpa.domain.Specification
import org.testng.util.Strings
import java.time.LocalDateTime
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class AbstractSpec<T>(
	open val model: AbstractNamedModel
	): Specification<T>  {

	override fun toPredicate(
		root: Root<T>,
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

		deletedAt(
			date = model.deletedAt,
			root = root,
			cb = cb,
			predicates = predicates,
		)

		return andTogether(predicates, cb)
	}


	fun equalsId(
		id: Long?,
		root: Root<*>,
		cb: CriteriaBuilder,
		predicates: MutableList<Predicate>
	){
		if(id != null){

			with(predicates){
				add(
					cb.equal(root.get<Int>("id"), id
					)
				)
			}
		}
	}

	fun nameLikeEnds(
		name: String?,
		root: Root<*>,
		cb: CriteriaBuilder,
		predicates: MutableList<Predicate>,
	) {
		if (Strings.isNotNullAndNotEmpty(name)) {
			with(predicates) {
				add(
					cb.like(
						cb.lower(root.get("name")),
						"%" + name?.lowercase() + "%"
					)
				)
			}
		}
	}

	fun deleted(
		deleted: Boolean?,
		root: Root<*>,
		cb: CriteriaBuilder,
		predicates: MutableList<Predicate>,
	) {

		when(deleted){
			false, true -> {
				with(predicates){
					add(
						cb.equal(root.get<Boolean>("name"), deleted
						)
					)
				}
			}
			null -> {
				with(predicates){
					add(
						cb.equal(root.get<Boolean>("name"), false
						)
					)
				}
			}
		}
	}

	fun createAtDateTime(
		date: LocalDateTime?,
		root: Root<*>,
		cb: CriteriaBuilder,
		predicates: MutableList<Predicate>,
	){

		if(date != null ) {
			val startingFrom = cb.greaterThanOrEqualTo(
				root.get("createdAt"),
				date
			)

			val endingAt =cb.lessThanOrEqualTo(
				root.get("createdAt"),
				date
			)

			predicates.add(cb.and(startingFrom, endingAt) )
		}
	}
	fun deletedAt(
		date: LocalDateTime?,
		root: Root<*>,
		cb: CriteriaBuilder,
		predicates: MutableList<Predicate>,
	){
		if(date == null ) {

			predicates.add(cb.isNull(root.get<LocalDateTime>("deletedAt")))
		} else {

			val startingFrom = cb.greaterThanOrEqualTo(
				root.get("deletedAt"),
				date
			)

			val endingAt =cb.lessThanOrEqualTo(
				root.get("deletedAt"),
				date
			)

			predicates.add(cb.and(startingFrom, endingAt) )
		}
	}

	fun lastModifiedAtDateTime(
		date: LocalDateTime?,
		root: Root<*>,
		cb: CriteriaBuilder,
		predicates: MutableList<Predicate>,
	){

		if(date != null ) {
			val startingFrom = cb.greaterThanOrEqualTo(
				root.get("lastModifiedAt"),
				date
			)

			val endingAt =cb.lessThanOrEqualTo(
				root.get("lastModifiedAt"),
				date
			)

			predicates.add(cb.and(startingFrom, endingAt) )
		}
	}

	fun andTogether(predicates: List<Predicate>, cb: CriteriaBuilder): Predicate? {
		return cb.and(*predicates.toTypedArray())
	}

}