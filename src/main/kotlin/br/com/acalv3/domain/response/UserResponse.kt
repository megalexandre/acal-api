package br.com.acalv3.domain.response

class UserResponse (
	val username: String? = "",
	val roles: List<String>? = mutableListOf(),
	val token: String? = "",
)