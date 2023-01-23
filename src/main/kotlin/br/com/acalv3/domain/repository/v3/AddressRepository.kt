package br.com.acalv3.domain.repository.v3

import br.com.acalv3.resources.model.AddressModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface AddressRepository : JpaRepository<AddressModel, Long>, JpaSpecificationExecutor<AddressModel> {
    fun findByName(name: String): AddressModel
}


