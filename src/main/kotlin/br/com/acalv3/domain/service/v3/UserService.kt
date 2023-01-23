package br.com.acalv3.domain.service.v3

import br.com.acalv3.resources.model.UserModel
import br.com.acalv3.domain.repository.v3.RoleRepository
import br.com.acalv3.domain.repository.v3.UserRepository
import br.com.acalv3.domain.service.AppService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.testng.util.Strings

@Service
class UserService (
	private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    ) : AppService<UserModel>(userRepository, userRepository), UserDetailsService {
	private var logger: Logger = LoggerFactory.getLogger(AppService::class.java)

	override fun findByName(name: String): UserModel = run {
		return userRepository.findByUsername(name) ?: throw UsernameNotFoundException("")
	}

	override fun loadUserByUsername(username: String): UserDetails {
		BCryptPasswordEncoder().encode("senha")
		val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found exception")
		user.roles = roleRepository.findByUser(user)

		return user
	}

	fun registerUser(userModel: UserModel) {

		if (Strings.isNullOrEmpty(userModel.username)) {
			logger.info(NAME_PARAMETER_IS_REQUIRED)
			throw RuntimeException(NAME_PARAMETER_IS_REQUIRED)
		}

		if(userModel.password.isEmpty()) {
			logger.info(PASSWORD_PARAMETER_IS_REQUIRED)
			throw RuntimeException(PASSWORD_PARAMETER_IS_REQUIRED)
		}

		BCryptPasswordEncoder().encode(userModel.password).also { userModel.password = it }
		super.prepareForSave(userModel)
		userRepository.save(userModel)
	}

	companion object{
		const val NAME_PARAMETER_IS_REQUIRED = "Nome é paramêtro obrigatório"
		const val PASSWORD_PARAMETER_IS_REQUIRED = "Senha é paramêtro obrigatório"
	}
}
