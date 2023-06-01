package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Place
import br.com.acalv3.resources.model.DefaultEntity
import java.util.UUID
import javax.persistence.CascadeType.DETACH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.springframework.data.domain.Page

@Entity(name = "place")
class PlaceEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(nullable = false)
    val letter: String,

    @Column(name = "address_id", nullable = false, columnDefinition = "BINARY(16)")
    val addressId: UUID,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(
        nullable = true,
        name="address_id", insertable = false, updatable = false)
    val address: AddressEntity? = null,

    @Column(nullable = false)
    val number: Long,

    val other: String?,

    val hasHydrometer: Boolean,

) : DefaultEntity()

fun Place.toPlaceEntity() = PlaceEntity(
    id = id,
    address = address?.toAddressEntity(),
    addressId = addressId,
    letter = letter,
    number = number,
    hasHydrometer = hasHydrometer,
    other = other,
)

fun PlaceEntity.toPlace() = Place(
    id = id,
    addressId = addressId,
    address = address?.toAddress(),
    letter = letter,
    number = number,
    hasHydrometer = hasHydrometer,
    other = other,
)

fun Page<PlaceEntity>.toPlacePage() = map{ it.toPlace() }
fun List<PlaceEntity>.toPlace() = map{ it.toPlace() }