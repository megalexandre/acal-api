package br.com.acalv3.domain.service.strategies.place

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Place

interface PlaceStrategy  {
    fun action(): Action
    fun can(place: Place)
}