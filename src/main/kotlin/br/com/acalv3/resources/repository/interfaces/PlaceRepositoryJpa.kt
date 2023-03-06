package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.PlaceEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PlaceRepositoryJpa : JpaRepository<PlaceEntity, UUID>, JpaSpecificationExecutor<PlaceEntity> {
}

