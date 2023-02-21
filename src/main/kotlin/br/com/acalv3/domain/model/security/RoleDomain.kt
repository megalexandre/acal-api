package br.com.acalv3.domain.model.security

import org.springframework.security.core.GrantedAuthority

data class RoleDomain (

    private val authority: String,

): GrantedAuthority {

    override fun getAuthority(): String {
        return authority
    }
}

