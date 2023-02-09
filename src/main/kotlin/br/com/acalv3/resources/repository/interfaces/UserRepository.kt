package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.security.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface UserRepository: JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {
	fun findByUsername(username: String): UserModel?
}
