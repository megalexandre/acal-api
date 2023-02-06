package br.com.acalv3.application.configuration.interceptor

import org.apache.logging.slf4j.SLF4JLoggerContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler: SLF4JLoggerContext(),  AccessDeniedHandler {
	private var logger: Logger = LoggerFactory.getLogger(CustomAccessDeniedHandler::class.java)

	override fun handle(
		request: HttpServletRequest?,
		response: HttpServletResponse?,
		accessDeniedException: AccessDeniedException?
	) {
		logger.info("denied access")
		response?.sendRedirect(request!!.contextPath.toString() + "/403")
	}
}