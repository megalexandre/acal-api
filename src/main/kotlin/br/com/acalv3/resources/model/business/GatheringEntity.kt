package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.resources.model.DefaultEntity
import java.util.UUID
import javax.persistence.CascadeType.DETACH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "gathering")
class GatheringEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(name = "quality_id", nullable = false, columnDefinition = "BINARY(16)")
    val qualityId: UUID,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="quality_id", insertable = false, updatable = false)
    val quality: QualityEntity? = null,

    @Enumerated(STRING)
    val param: Param,

    val required: Long,

    val analyzed: Long,

    val conformity: Long,


) : DefaultEntity()

fun Gathering.toGatheringEntity() = GatheringEntity(
    id = id,
    param = param,
    required = required,
    analyzed = analyzed,
    conformity = conformity,
    qualityId = qualityId,
)

fun GatheringEntity.toGathering() = Gathering(
    id = id,
    param = param,
    required = required,
    analyzed = analyzed,
    conformity = conformity,
    qualityId = qualityId,
)

fun List<GatheringEntity>.toGathering(): List<Gathering>  = map{ it.toGathering() }
fun List<Gathering>.toGatheringEntity(): List<GatheringEntity> = map{ it.toGatheringEntity() }