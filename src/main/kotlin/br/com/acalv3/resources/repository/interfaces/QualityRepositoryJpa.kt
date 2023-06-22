package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.QualityEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface QualityRepositoryJpa : JpaRepository<QualityEntity, UUID>, JpaSpecificationExecutor<QualityEntity>{
    fun findByReference(reference: String): QualityEntity?
    fun findByReferenceIn(references: List<String>): List<QualityEntity>?
}