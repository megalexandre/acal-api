package br.com.acalv3.application.comunicate.model.response.place

import br.com.acalv3.domain.model.Place
import java.util.UUID

class SaveUpdatePlaceResponse(
    val id: UUID
)

fun Place.toPlaceResponse() = SaveUpdatePlaceResponse(
    id = id
)