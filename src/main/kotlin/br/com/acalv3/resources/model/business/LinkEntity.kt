package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.domain.model.Link
import br.com.acalv3.resources.model.DefaultEntity
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.data.domain.Page
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.DETACH
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME

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
    val mailPlace: PlaceEntity?,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="group_id")
    val group: GroupEntity,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="customer_id")
    val customer: CustomerEntity,

    @CreationTimestamp
    @DateTimeFormat(pattern = Fixture.DATE_FORMAT, iso = DATE_TIME)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @DateTimeFormat(pattern = Fixture.DATE_FORMAT, iso = DATE_TIME)
    val updatedAt: LocalDateTime? = null,

    val active: Boolean

    ) : DefaultEntity()

fun Link.toLinkEntity() = LinkEntity(
    id = UUID.fromString(id),
    place = place.toPlaceEntity(),
    mailPlace = place.toPlaceEntity(),
    group = group.toGroupEntity(),
    customer = customer.toCustomerEntity(),
    active = active,
    name = ""
)

fun LinkEntity.toLink() = Link(
    id = id.toString(),
    place = place.toPlace(),
    mailPlace = place.toPlace(),
    group = group.toGroup(),
    customer = customer.toCustomer(),
    active = active,
)

fun Page<LinkEntity>.toLinkPage() = map{ it.toLink() }
fun List<LinkEntity>.toLink() = map{ it.toLink() }