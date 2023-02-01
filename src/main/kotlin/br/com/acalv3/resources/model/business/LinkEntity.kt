package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Link
import org.springframework.data.domain.Page
import java.util.*
import javax.persistence.*

@Entity(name = "link")
class LinkEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val name: String,

    @ManyToOne
    @JoinColumn(name="customer_id")
    val customerEntity: CustomerEntity
)

fun Link.toLinkEntity() = LinkEntity(
    id = id,
    name = name,
    customerEntity = customer.toCustomerModel()
)

fun LinkEntity.toLink() = Link(
    id = id,
    name = name,
    customer = customerEntity.toCustomer(),
)

fun Page<LinkEntity>.toLinkPage() = map{ it.toLink() }