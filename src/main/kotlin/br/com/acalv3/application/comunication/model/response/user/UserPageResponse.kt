package br.com.acalv3.application.comunication.model.response.user

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.domain.model.security.RoleDomain
import org.springframework.data.domain.Page

data class UserPageResponse(
    val id: String?,
    val username: String?,
    val roles: List<RoleDomain>?
)

fun UserPage.toResponse() = UserPageResponse(
    id = id,
    username = username,
    roles = roles,
)

fun Page<UserPage>.toResponse() = map{ it.toResponse() }