package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.domain.model.security.UserDomain

interface UserRepository:  AbstractRepository<UserDomain, UserPage> {
    fun findByUsername(name: String): UserDomain?
    fun existByUsername(name: String): Boolean
}