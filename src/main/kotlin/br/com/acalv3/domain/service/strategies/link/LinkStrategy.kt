package br.com.acalv3.domain.service.strategies.link

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Link

interface LinkStrategy  {
    fun action(): Action
    fun can(link: Link)
}