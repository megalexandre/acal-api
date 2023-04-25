package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.AddressEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface AddressRepositoryJpa : JpaRepository<AddressEntity, UUID>, JpaSpecificationExecutor<AddressEntity> {
    fun findByName(name: String): AddressEntity?
}

