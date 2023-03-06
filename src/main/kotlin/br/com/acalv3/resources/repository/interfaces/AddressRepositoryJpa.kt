package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface AddressRepositoryJpa : JpaRepository<AddressEntity, UUID>, JpaSpecificationExecutor<AddressEntity> {
    fun findByName(name: String): AddressEntity?
}

