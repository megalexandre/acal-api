package br.com.acalv3.resources.repository

import br.com.acalv3.resources.model.business.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface CustomerRepositoryJpa : JpaRepository<CustomerModel, UUID>, JpaSpecificationExecutor<CustomerModel> {
    fun findByName(name: String): CustomerModel
}

