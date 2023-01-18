package br.com.acalv3.domain.exception

class DuplicatedFieldException(

    override val message: String

) : RuntimeException(message)