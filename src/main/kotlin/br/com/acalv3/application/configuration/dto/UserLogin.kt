package br.com.acalv3.application.configuration.dto

import org.springframework.security.core.GrantedAuthority

data class UserLogin(
    val username: String?,
    val password: String?,
    val authorities: List<Role>?,
    val token: String?
)

data class Role(
    val name: String? = "",
): GrantedAuthority {

    override fun getAuthority() = name
}


