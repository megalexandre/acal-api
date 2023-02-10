package br.com.acalv3.application.comunicate.model.request.place

import br.com.acalv3.application.comunicate.model.request.address.AddressSaveRequest

open class PlaceRequest (
    open val number: Long? = null,
    open val letter: String? = null,
    open val address: AddressSaveRequest? = null,
)