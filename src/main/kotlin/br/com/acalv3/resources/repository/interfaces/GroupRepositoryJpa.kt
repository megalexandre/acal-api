package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.GroupEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface GroupRepositoryJpa : JpaRepository<GroupEntity, UUID>, JpaSpecificationExecutor<GroupEntity> {
    fun findByName(name: String): GroupEntity
}

