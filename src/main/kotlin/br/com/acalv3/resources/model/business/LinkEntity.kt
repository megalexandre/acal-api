package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Link
import br.com.acalv3.resources.model.DefaultEntity
import org.springframework.data.domain.Page
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.DETACH

@Entity(name = "link")
class LinkEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val name: String,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="place_id")
    val place: PlaceEntity,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="place_address_id")
    val placeAddress: PlaceEntity?,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="group_id")
    val group: GroupEntity,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="customer_id")
    val customer: CustomerEntity

) : DefaultEntity()

fun Link.toLinkEntity() = LinkEntity(
    id = UUID.fromString(id),
    place = place.toPlaceEntity(),
    placeAddress = place.toPlaceEntity(),
    group = group.toGroupEntity(),
    customer = customer.toCustomerEntity(),
    name = ""
)

fun LinkEntity.toLink() = Link(
    id = id.toString(),
    place = place.toPlace(),
    placeAddress = place.toPlace(),
    group = group.toGroup(),
    customer = customer.toCustomer(),
)

fun Page<LinkEntity>.toLinkPage() = map{ it.toLink() }
fun List<LinkEntity>.toLink() = map{ it.toLink() }