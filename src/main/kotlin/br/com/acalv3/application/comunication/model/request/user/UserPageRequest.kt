package br.com.acalv3.application.comunication.model.request.user

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.domain.model.security.RoleDomain

data class UserPageRequest(
    val id: String? = null,
    val username: String? = null,
    val password: String? = null,
    val authorities: List<RoleDomain>? = null,
)

fun UserPageRequest.toUserPage() = UserPage(
    id = id,
    username = username,
)

