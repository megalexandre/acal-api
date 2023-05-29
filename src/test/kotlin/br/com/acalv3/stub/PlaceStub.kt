package br.com.acalv3.stub

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Place
import java.util.UUID

fun placeStub(
    id: UUID = UUID.randomUUID(),
    number: Long = 1,
    letter: String = "A",
    address: Address = addressStub(),
    addressId: UUID = address.id,
    hasHydrometer: Boolean = true,
    other: String? = "outher",
) = Place(
    id = id,
    number = number,
    letter = letter,
    address = address,
    addressId = addressId,
    hasHydrometer = hasHydrometer,
    other = other,
)