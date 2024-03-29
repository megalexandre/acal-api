package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.security.UserEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepositoryJpa: JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
	fun findByUsername(username: String): UserEntity?
	fun existsByUsername(username: String): Boolean
}
