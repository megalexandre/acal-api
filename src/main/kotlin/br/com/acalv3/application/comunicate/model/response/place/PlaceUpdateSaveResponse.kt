package br.com.acalv3.application.comunicate.model.response.place

import br.com.acalv3.domain.model.Place

class SaveUpdatePlaceResponse(
    val id: String
)

fun Place.toPlaceResponse() = SaveUpdatePlaceResponse(
    id = id
)