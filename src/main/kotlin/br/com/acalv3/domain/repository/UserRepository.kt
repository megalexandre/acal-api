package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.security.UserDomain

interface UserRepository {
    fun findByUsername(name: String): UserDomain
}