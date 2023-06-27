package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.HydrometerEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface HydrometerRepositoryJpa : JpaRepository<HydrometerEntity, UUID>, JpaSpecificationExecutor<HydrometerEntity>{
    fun findByReferenceAndLinkId(reference: String, id: UUID):  HydrometerEntity?
    fun findByReference(reference: String): List<HydrometerEntity>?
}
