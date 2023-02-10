package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.PlaceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface PlaceRepositoryJpa : JpaRepository<PlaceEntity, UUID>, JpaSpecificationExecutor<PlaceEntity> {
}

