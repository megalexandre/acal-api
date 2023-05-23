package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.resources.model.DefaultEntity
import java.util.UUID
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.Id
import javax.persistence.OneToMany
import org.springframework.data.domain.Page

@Entity(name = "quality")
data class QualityEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val startedAt: String,

) : DefaultEntity() {

    @OneToMany(mappedBy="quality", cascade = [ALL], fetch = EAGER)
    var gathering: List<GatheringEntity>? = null

}

fun Quality.toEntity() = QualityEntity(
    id = id,
    startedAt = startedAt,
).also {
    it.gathering = gathering?.toGatheringEntity(it.toDomain())
}

fun QualityEntity.toDomain() = Quality(
    id = id,
    startedAt = startedAt,
).also {
    it.gathering = this.gathering?.toGathering(it)
}

fun List<QualityEntity>.toDomain() = map{ it.toDomain() }
fun Page<QualityEntity>.toPage() = map{ it.toDomain() }