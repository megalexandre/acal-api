package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.BOOK_ROUTER
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunication.model.request.book.BookPageRequest
import br.com.acalv3.application.comunication.model.request.book.toPage
import br.com.acalv3.application.comunication.model.response.book.BookPageResponse
import br.com.acalv3.application.comunication.model.response.book.toPageResponse
import br.com.acalv3.domain.service.BookService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BOOK_ROUTER, produces=[APPLICATION_JSON_VALUE])
class BookController(
    val service: BookService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping(PAGINATE)
    fun paginate(@Valid @RequestBody request: BookPageRequest): Page<BookPageResponse> =
        service.paginate(request.toPage()).toPageResponse().also {
            logger.info("book paginate: $request")
        }

}