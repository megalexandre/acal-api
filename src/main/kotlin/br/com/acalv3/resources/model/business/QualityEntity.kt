package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.resources.model.DefaultEntity
import java.time.LocalDate
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "quality")
class QualityEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val startedAt: LocalDate,

    val finishedAt: LocalDate,

) : DefaultEntity() {

    @OneToMany(mappedBy="quality", cascade = [CascadeType.ALL])
    var gathering: List<GatheringEntity>? = null
}

fun Quality.toQualityEntity() = QualityEntity(
    id = UUID.fromString(id),
    startedAt = startedAt,
    finishedAt = finishedAt,
).also {
    it.gathering = gathering?.toGatheringEntity(it.toQuality())
}

fun QualityEntity.toQuality() = Quality(
    id = id.toString(),
    startedAt = startedAt,
    finishedAt = finishedAt,
).also {
    it.gathering = this.gathering?.toGathering(it)
}