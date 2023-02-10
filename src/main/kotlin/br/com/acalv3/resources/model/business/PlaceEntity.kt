package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Place
import br.com.acalv3.resources.model.DefaultEntity
import org.springframework.data.domain.Page
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.MERGE

@Entity(name = "place")
class PlaceEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(nullable = false)
    val letter: String,

    @ManyToOne(cascade = [MERGE])
    @JoinColumn(name="address_id")
    val address: AddressEntity,

    @Column(nullable = false)
    val number: Long,

) : DefaultEntity()

fun Place.toPlaceEntity() = PlaceEntity(
    id = UUID.fromString(id),
    address = address.toAddressEntity(),
    letter = letter,
    number = number,
)

fun PlaceEntity.toPlace() = Place(
    id = id.toString(),
    address = address.toAddress(),
    letter = letter,
    number = number,
)

fun Page<PlaceEntity>.toPlacePage() = map{ it.toPlace() }