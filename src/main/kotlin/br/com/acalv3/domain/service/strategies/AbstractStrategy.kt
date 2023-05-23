package br.com.acalv3.domain.service.strategies

import br.com.acalv3.domain.enumeration.Action

interface AbstractStrategy<Model>  {
    fun action(): Action
    fun can(model: Model)
}