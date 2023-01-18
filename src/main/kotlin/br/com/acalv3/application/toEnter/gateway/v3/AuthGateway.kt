package br.com.acalv3.application.toEnter.gateway.v3

import br.com.acalv3.domain.model.v3.UserModel
import br.com.acalv3.domain.service.v3.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthGateway(
	val service: UserService
){
	@PostMapping("register")
	fun register(@RequestBody userModel: UserModel) = run {
		service.registerUser(userModel)
	}
}