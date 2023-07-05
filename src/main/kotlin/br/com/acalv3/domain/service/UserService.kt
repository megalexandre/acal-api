package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.domain.model.security.UserDomain
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
abstract class UserService: UserDetailsService{

	abstract override fun loadUserByUsername(username: String): UserDetails?
	abstract fun save(userDomain: UserDomain): UserDomain
	abstract fun update(userDomain: UserDomain): UserDomain
	abstract fun paginate(page: UserPage): Page<UserDomain>
	abstract fun getById(id: String): UserDomain
}
