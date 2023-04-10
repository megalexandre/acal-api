package br.com.acalv3.domain.exception

class UnauthorizedException(

    override val message: String

) : RuntimeException(message)