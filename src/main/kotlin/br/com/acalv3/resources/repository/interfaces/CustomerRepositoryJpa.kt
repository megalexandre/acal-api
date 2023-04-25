package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.business.CustomerEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CustomerRepositoryJpa : JpaRepository<CustomerEntity, UUID>, JpaSpecificationExecutor<CustomerEntity> {
    fun findByName(name: String): CustomerEntity
    fun findByDocument(document: String): CustomerEntity?
}

