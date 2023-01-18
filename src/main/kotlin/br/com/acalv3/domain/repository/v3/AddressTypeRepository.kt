package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.AddressTypeModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface AddressTypeRepository : JpaRepository<AddressTypeModel, Long>, JpaSpecificationExecutor<AddressTypeModel> {
    fun findByName(name: String): AddressTypeModel
}