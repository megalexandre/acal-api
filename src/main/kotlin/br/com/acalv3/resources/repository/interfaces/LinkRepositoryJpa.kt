package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.LinkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface LinkRepositoryJpa : JpaRepository<LinkEntity, UUID>, JpaSpecificationExecutor<LinkEntity> {
    fun findByName(name: String): LinkEntity
    fun findByCustomerId(customerId: UUID): List<LinkEntity>
    fun findByGroupId(customerId: UUID): List<LinkEntity>
}

