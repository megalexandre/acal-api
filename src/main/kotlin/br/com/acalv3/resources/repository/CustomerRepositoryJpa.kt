package br.com.acalv3.resources.repository

import br.com.acalv3.resources.model.business.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CustomerRepositoryJpa : JpaRepository<CustomerModel, Long>, JpaSpecificationExecutor<CustomerModel> {
    fun findByName(name: String): CustomerModel
}

