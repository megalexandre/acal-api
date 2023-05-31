package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.resources.model.DefaultEntity
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.CascadeType.DETACH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.springframework.data.domain.Page

@Entity(name = "hydrometer")
class HydrometerEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val reference: String,

    val costValue: BigDecimal,

    val consumption: Long,

    @Column(name = "link_id", nullable = false, columnDefinition = "BINARY(16)")
    val linkId: UUID,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="link_id", insertable = false, updatable = false)
    var link: LinkEntity? = null

) : DefaultEntity()

fun Hydrometer.toEntity() = HydrometerEntity(
    id = id,
    reference = reference,
    costValue = costValue,
    consumption = consumption,
    linkId = linkId,
).also {
    it.link = link?.toLinkEntity()
}

fun HydrometerEntity.toDomain() = Hydrometer(
    id = id,
    reference = reference,
    costValue = costValue,
    consumption = consumption,
    link = link?.toLink(),
    linkId = linkId,
)

fun HydrometerEntity.toDomainWithoutLink() = Hydrometer(
    id = id,
    reference = reference,
    costValue = costValue,
    consumption = consumption,
    link = link?.toLinkWithSafeHydrometer(),
    linkId = linkId,
)

fun Page<HydrometerEntity>.toPage() = map{ it.toDomainWithoutLink() }
fun List<HydrometerEntity>.toDomain(): List<Hydrometer> = map{ it.toDomainWithoutLink() }
fun List<HydrometerEntity>.toDomainWithoutLink(): List<Hydrometer> = map{ it.toDomainWithoutLink() }
fun List<Hydrometer>.toEntity(): List<HydrometerEntity> = map{ it.toEntity() }