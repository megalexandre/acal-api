package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.domain.model.Link
import br.com.acalv3.resources.model.DefaultEntity
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType.DETACH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.domain.Page
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
    val mailPlace: PlaceEntity,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="group_id")
    val group: GroupEntity,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="customer_id")
    val customer: CustomerEntity,

    @OneToMany(mappedBy="link")
    val invoice: List<InvoiceEntity>? = null,

    @CreationTimestamp
    @DateTimeFormat(pattern = Fixture.DATE_FORMAT, iso = DATE_TIME)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @DateTimeFormat(pattern = Fixture.DATE_FORMAT, iso = DATE_TIME)
    val updatedAt: LocalDateTime? = null,

    val active: Boolean,

    val startedAt: LocalDateTime,

    val finishedAt: LocalDateTime? = null

    ) : DefaultEntity(){

    @OneToMany(mappedBy="link")
    var hydrometers: List<HydrometerEntity>? = null
}

fun Link.toLinkEntity() = LinkEntity(
    id = id,
    place = place.toPlaceEntity(),
    mailPlace = mailPlace.toPlaceEntity(),
    group = group.toGroupEntity(),
    customer = customer.toCustomerEntity(),
    active = active,
    name = "",
    startedAt = startedAt,
    finishedAt = finishedAt,
)

fun LinkEntity.toLink() = Link(
    id = id,
    place = place.toPlace(),
    mailPlace = mailPlace.toPlace(),
    group = group.toGroup(),
    customer = customer.toCustomer(),
    active = active,
    startedAt = startedAt,
    finishedAt = finishedAt,
)

fun LinkEntity.toLinkWithSafeHydrometer() = Link(
    id = id,
    place = place.toPlace(),
    mailPlace = mailPlace.toPlace(),
    group = group.toGroup(),
    customer = customer.toCustomer(),
    active = active,
    startedAt = startedAt,
    finishedAt = finishedAt,
)

fun Page<LinkEntity>.toLinkPage() = map{ it.toLink() }
fun List<LinkEntity>.toLink() = map{ it.toLink() }
fun List<LinkEntity>.toLinkSafeHydrometer() = map{ it.toLinkWithSafeHydrometer() }