package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.GroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface GroupRepositoryJpa : JpaRepository<GroupEntity, UUID>, JpaSpecificationExecutor<GroupEntity> {
    fun findByName(name: String): GroupEntity
}

