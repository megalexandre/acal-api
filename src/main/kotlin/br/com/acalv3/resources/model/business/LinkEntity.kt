package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Link
import br.com.acalv3.resources.model.DefaultEntity
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

) : DefaultEntity()

fun Link.toLinkEntity() = LinkEntity(
    id = UUID.fromString(id),
    name = name,
    customerEntity = customer.toCustomerEntity()
)

fun LinkEntity.toLink() = Link(
    id = id.toString(),
    name = name,
    customer = customerEntity.toCustomer(),
)

fun Page<LinkEntity>.toLinkPage() = map{ it.toLink() }