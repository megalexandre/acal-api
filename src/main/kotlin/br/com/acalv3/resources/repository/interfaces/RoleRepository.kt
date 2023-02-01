package br.com.acalv3.resources.repository.interfaces

import br.com.acalv3.resources.model.security.RoleModel
import br.com.acalv3.resources.model.security.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<RoleModel, Long>{
	fun findByUser(user: UserModel): List<RoleModel>?
}
