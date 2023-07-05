package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.domain.model.security.UserDomain
import br.com.acalv3.domain.repository.UserRepository
import br.com.acalv3.domain.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
	private val repository: UserRepository
): UserService() {
	private var logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun save(userDomain: UserDomain): UserDomain = repository.save(userDomain.copy(password =
		BCryptPasswordEncoder().encode(userDomain.password)
	))

	override fun update(userDomain: UserDomain): UserDomain = repository.save(userDomain)

	override fun paginate(page: UserPage): Page<UserDomain> = repository.paginate(page)

	override fun getById(id: String): UserDomain = repository.getById(id)

	override fun loadUserByUsername(username: String): UserDetails?  {
		logger.info("Getting username $username")

		return repository.findByUsername(username) ?:
			throw UsernameNotFoundException("User $username was't found").also {
			logger.info("findByName returning $it")
		}
	}

}