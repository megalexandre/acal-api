package br.com.acalv3.domain.repository.v3

import br.com.acalv3.resources.model.security.RoleModel
import br.com.acalv3.resources.model.security.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<RoleModel, Long>{
	fun findByUser(user: UserModel): List<RoleModel>?
}
