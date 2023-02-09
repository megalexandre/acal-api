package br.com.acalv3.domain.service

import br.com.acalv3.application.configuration.dto.UserLogin
import br.com.acalv3.resources.model.security.RoleModel
import br.com.acalv3.resources.model.security.UserModel
import br.com.acalv3.resources.model.security.toUserLogin
import br.com.acalv3.resources.repository.interfaces.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    ): UserDetailsService {

	fun findByName(name: String): UserLogin =
		userRepository.findByUsername(name)?.toUserLogin() ?: throw UsernameNotFoundException("User not found: $name")

	override fun loadUserByUsername(username: String): UserDetails {
		val user: UserModel = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found: $username")
		user.roles = listOf(RoleModel( authority = "ADMIN"))
		return user
	}

}
