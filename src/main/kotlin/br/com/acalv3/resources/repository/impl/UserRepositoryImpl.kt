package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.security.UserDomain
import br.com.acalv3.domain.repository.UserRepository
import br.com.acalv3.resources.model.security.toUserDomain
import br.com.acalv3.resources.model.security.toUserEntity
import br.com.acalv3.resources.repository.interfaces.UserRepositoryJpa
import org.springframework.stereotype.Repository


@Repository
class UserRepositoryImpl(
    private val repository: UserRepositoryJpa,
) : UserRepository {

    override fun findByUsername(name: String): UserDomain =
        repository.findByUsername(name)?.toUserDomain() ?: throw RuntimeException("")

    override fun save(user: UserDomain): UserDomain  =
        repository.save(user.toUserEntity()).toUserDomain()

}