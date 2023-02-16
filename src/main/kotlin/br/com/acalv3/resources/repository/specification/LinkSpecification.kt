package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.LinkPage
import br.com.acalv3.resources.model.business.LinkEntity
import java.math.BigDecimal
import java.util.UUID
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class LinkSpecification(private val link: LinkPage) {

    fun getSpecification(): Specification<LinkEntity> =
        Specification<LinkEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<LinkEntity>, builder: CriteriaBuilder) {

        if (!link.customer?.name.isNullOrEmpty()) {
            predicate.expressions.add(likeCustomerName(root, builder))
        }

        if (!link.customer?.document.isNullOrEmpty()) {
            predicate.expressions.add(eqCustomerDocument(root, builder))
        }

        if(!link.group?.name.isNullOrEmpty()){
            predicate.expressions.add(likeGroupName(root, builder))
        }

        if(link.group?.category != null){
            predicate.expressions.add(eqGroupCategory(root, builder))
        }

        if(link.group?.value != null){
            predicate.expressions.add(eqGroupValue(root, builder))
        }

        if(!link.place?.address?.id.isNullOrEmpty()){
            predicate.expressions.add(eqAddressId(root, builder))
        }

    }

    private fun eqAddressId(root: Root<LinkEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(
            root.get<Place>("place").get<Address>("address").get<UUID>("id"),
            UUID.fromString(link.place?.address?.id)
        )

    private fun eqGroupValue(root: Root<LinkEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<Group>("group").get<BigDecimal>("value"), link.group?.value)

    private fun eqGroupCategory(root: Root<LinkEntity>, builder: CriteriaBuilder): Predicate =
        builder.equal(root.get<Group>("group").get<String>("category"), link.group?.category)

    private fun likeGroupName(root: Root<LinkEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get<Group>("group").get("name")), "%${link.group?.name}%")

    private fun likeCustomerName(root: Root<LinkEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get<Customer>("customer").get("name")), "%${link.customer?.name}%")

    private fun eqCustomerDocument(root: Root<LinkEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get<Customer>("customer").get("document")), "%${link.customer?.document}%")

}
