package br.com.acalv3.domain.exception

class RequiredFieldException(

    ex: Exception,
    override val message: String

) : RuntimeException(message, ex)