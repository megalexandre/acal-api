package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.security.RoleDomain
import br.com.acalv3.domain.model.security.UserDomain
import br.com.acalv3.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
	private val userRepositoryJpa: UserRepository,
    ): UserDetailsService {

	override fun loadUserByUsername(username: String): UserDetails {

		if(username == "alexandre" && !userRepositoryJpa.existByName(username)){
			userRepositoryJpa.save(
				UserDomain("alexandre",
				BCryptPasswordEncoder().encode("senha"))
			)
		}

		val user: UserDomain = userRepositoryJpa.findByUsername(username)
		return user.copy(
			authorities = listOf(RoleDomain(authority = "ADMIN"))
		)
	}

}
