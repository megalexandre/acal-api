package br.com.acalv3.application.comunication.model.response.user

import br.com.acalv3.domain.model.security.UserDomain
import org.springframework.data.domain.Page

data class UserResponse(
    val id: String?,
    val username: String,
    val authorities: List<String>? = listOf(),
)

fun UserDomain.toResponse() = UserResponse(
    id = getId(),
    username = username,
    authorities = authorities?.map { it.authority }
)

fun Page<UserDomain>.toResponse() = map{ it.toResponse() }
fun List<UserDomain>.toResponse() = map{ it.toResponse() }