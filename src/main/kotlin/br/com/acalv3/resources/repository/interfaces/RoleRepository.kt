package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.security.RoleEntity
import br.com.acalv3.resources.model.security.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<RoleEntity, Long>{
	fun findByUser(user: UserEntity): List<RoleEntity>?
}
