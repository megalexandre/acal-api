package br.com.acalv3.application.configuration.dto

import org.springframework.security.core.GrantedAuthority

data class UserLogin(
    val username: String?,
    var password: String? = null,
    var authorities: List<Role>? = null,
    var token: String? = null
)

data class Role(
    val name: String? = "",
): GrantedAuthority {

    override fun getAuthority() = name
}

