package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.security.RoleDomain
import br.com.acalv3.domain.model.security.UserDomain
import br.com.acalv3.domain.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
	private val userRepositoryJpa: UserRepository,
    ): UserDetailsService {
	private var logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun loadUserByUsername(username: String): UserDetails?  {
		logger.info("Getting username $username")

		userRepositoryJpa.existByUsername("alexandre").let {
			if(!it){
				userRepositoryJpa.save(UserDomain(
					username ="alexandre",
					password = BCryptPasswordEncoder().encode("senha"))
				)
			}
		}

		val user: UserDomain? = userRepositoryJpa.findByUsername(username).also {
			logger.info("findByName returning $it")
		}

		return user?.copy(
			authorities = listOf(RoleDomain(authority = "Programador"))
		) ?: throw UsernameNotFoundException("User $username was't found")
	}

}
