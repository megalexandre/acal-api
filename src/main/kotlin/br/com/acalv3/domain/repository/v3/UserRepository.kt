package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository: JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
	fun findByUsername(username: String): UserModel?
}
