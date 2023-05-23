package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.model.Gathering
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.resources.model.DefaultEntity
import java.util.UUID
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

    @Enumerated(STRING)
    val param: Param,

    val required: Long,

    val analyzed: Long,

    val conformity: Long,

    ) : DefaultEntity() {

    @ManyToOne
    @JoinColumn(name="quality_id")
    var quality: QualityEntity? = null
}

fun Gathering.toGatheringEntity(quality: Quality) = GatheringEntity(
    id = id,
    param = param,
    required = required,
    analyzed = analyzed,
    conformity = conformity,
).also {
    it.quality = quality.toEntity()
}

fun GatheringEntity.toGathering(quality: Quality) = Gathering(
    id = id,
    param = param,
    required = required,
    analyzed = analyzed,
    conformity = conformity,
    quality = quality
)

fun List<GatheringEntity>.toGathering(quality: Quality): List<Gathering>  = map{ it.toGathering(quality) }
fun List<Gathering>.toGatheringEntity(quality: Quality): List<GatheringEntity> = map{ it.toGatheringEntity(quality) }