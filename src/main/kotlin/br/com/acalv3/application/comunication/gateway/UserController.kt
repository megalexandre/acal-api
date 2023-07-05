package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.model.request.user.UserPageRequest
import br.com.acalv3.application.comunication.model.request.user.UserRequest
import br.com.acalv3.application.comunication.model.request.user.toDomain
import br.com.acalv3.application.comunication.model.request.user.toUserPage
import br.com.acalv3.application.comunication.model.response.user.UserResponse
import br.com.acalv3.application.comunication.model.response.user.toResponse
import br.com.acalv3.domain.service.UserService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user",
    produces=[APPLICATION_JSON_VALUE],
)
class UserController(
    val service: UserService,
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): UserResponse =
        service.getById(id).toResponse().also {
            logger.info("user getById: $id")
        }

    @PostMapping
    fun save(@RequestBody request: UserRequest): UserResponse =
        service.save(request.toDomain()).toResponse().also {
            logger.info("save: $request")
        }

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: UserRequest): UserResponse =
        service.update(request.toDomain()).toResponse().also {
            logger.info("update: $request")
        }

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: UserPageRequest): Page<UserResponse> =
        service.paginate(request.toUserPage()).toResponse().also {
            logger.info("paginate: $request")
        }

}