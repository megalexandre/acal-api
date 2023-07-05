package br.com.acalv3.application.comunication.model.request.user

import br.com.acalv3.domain.model.security.RoleDomain
import br.com.acalv3.domain.model.security.UserDomain

data class UserRequest(
    val id: String?,
    val username: String,
    val password: String,
    val authorities: List<RoleDomain>? = listOf(),
)

fun UserRequest.toDomain() = UserDomain(
    id = id,
    username = username,
    password = password,
    authorities = authorities,
)
