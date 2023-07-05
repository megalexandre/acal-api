package br.com.acalv3.domain.model.security

import org.springframework.security.core.userdetails.UserDetails

data class UserDomain(

    private val id: String? = null,

    private val username: String,

    private var password: String,

    private val authorities: List<RoleDomain>? = listOf(),

    var token: String? = null,

): UserDetails {

    fun getId(): String? = id

    override fun getAuthorities(): List<RoleDomain>? = authorities

    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String){
        this.password = password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

