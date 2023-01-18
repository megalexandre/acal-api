package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.ContractModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ContractRepository : JpaRepository<ContractModel, Long>,JpaSpecificationExecutor<ContractModel> {
    fun findByName(name: String): ContractModel
}