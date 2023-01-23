package br.com.acalv3.application.configuration.advice

import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.exception.RequiredFieldException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLException
import java.time.LocalDateTime

@ControllerAdvice
class AppAdvice {

	@ExceptionHandler(value = [
		EmptyResultDataAccessException::class, NoSuchElementException::class])
	fun e1 (ex: RuntimeException) =
		getResponse(ex, NO_CONTENT)

	@ExceptionHandler(value = [
		ConstraintViolationException::class, DuplicatedFieldException::class])
	fun e2 (ex: RuntimeException) =
		getResponse(ex, BAD_REQUEST)

	@ExceptionHandler(value = [
		RequiredFieldException::class])
	fun e3 (ex: SQLException) = run {

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

	fun getResponse(ex: Exception, status: HttpStatus, error: String? = null ){
		val body: MutableMap<String, Any?> = LinkedHashMap()
		body["timestamp"] = LocalDateTime.now()
		body["error"] = error?.let {ex.message} ?: error

		ResponseEntity(
			body,
			BAD_REQUEST
		)
	}

}