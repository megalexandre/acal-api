package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.LinkEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface LinkRepositoryJpa : JpaRepository<LinkEntity, UUID>, JpaSpecificationExecutor<LinkEntity> {
    fun findByName(name: String): LinkEntity?
    fun findByCustomerId(customerId: UUID): List<LinkEntity>?
    fun findByGroupId(groupId: UUID): List<LinkEntity>?
    fun findByPlaceId(placeId: UUID): List<LinkEntity>?
    fun findByPlaceIdAndActive(placeId: UUID, active: Boolean): List<LinkEntity>?

    @Query("SELECT count(l) FROM link l where l.active = true")
    fun countActive(): Long

    //@Query("SELECT SUM(l.group.value) FROM link l where l.active = true")
    //fun invoicing(): Long

}
