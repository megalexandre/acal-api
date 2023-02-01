package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.security.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository: JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
	fun findByUsername(username: String): UserModel?
}
