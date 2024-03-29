package br.com.acalv3.application.configuration.advice

import br.com.acalv3.commons.clearMessage
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.exception.InvoiceNotFoundException
import br.com.acalv3.domain.exception.RequiredFieldException
import br.com.acalv3.domain.exception.UnauthorizedException
import io.jsonwebtoken.ExpiredJwtException
import java.sql.SQLException
import java.time.LocalDateTime
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AppAdvice {
	private var logger: Logger = LoggerFactory.getLogger(this::class.java)

	@ExceptionHandler(value = [
		EmptyResultDataAccessException::class,
		NoSuchElementException::class])
	fun e1 (ex: RuntimeException) = getResponse(ex, NO_CONTENT)

	@ExceptionHandler(value = [
		ConstraintViolationException::class,
		DuplicatedFieldException::class,
		MethodArgumentNotValidException::class,
		InvoiceNotFoundException::class
	])
	fun e2 (ex: RuntimeException) = getResponse(ex, BAD_REQUEST)


	@ExceptionHandler(value = [
		RuntimeException::class])
	fun e4 (ex: RuntimeException) = getResponse(ex,INTERNAL_SERVER_ERROR).also {
		logger.error("",ex)
	}

	@ExceptionHandler(value = [
		RequiredFieldException::class])
	fun e3 (ex: SQLException)  {

		val start = ex.message?.indexOf("=(", 0, false)?.plus(2)
		val end = ex.message?.lastIndexOf(")")

		val error = ex.message?.subSequence(
			start!!,
			end!!
		).toString()

		getResponse(
			ex, BAD_REQUEST, error
		)
	}

	@ExceptionHandler(value = [
		ExpiredJwtException::class,
		UnauthorizedException::class])
	fun e3 (ex: UnauthorizedException)  {
		getResponse(
			ex, UNAUTHORIZED, "Voce não Possui Permissão, faça o login"
		)
	}


	fun getResponse(ex: Exception, status: HttpStatus, error: String? = null ) = run {
		logger.info(status.name)

		val body: MutableMap<String, Any?> = LinkedHashMap()
		body["timestamp"] = LocalDateTime.now()
		body["detail"] = ex.message
		body["error"] = ex.message?.let { error  } ?: ex.message?.clearMessage()

		ResponseEntity(
			body,
			BAD_REQUEST
		)
	}
}