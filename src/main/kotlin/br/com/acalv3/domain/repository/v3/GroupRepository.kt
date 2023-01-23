package br.com.acalv3.domain.repository.v3

import br.com.acalv3.resources.model.GroupModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface GroupRepository : JpaRepository<GroupModel, Long>, JpaSpecificationExecutor<GroupModel> {
    fun findByName(name: String): GroupModel
}