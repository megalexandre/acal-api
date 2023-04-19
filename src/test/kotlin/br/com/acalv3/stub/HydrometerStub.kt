package br.com.acalv3.stub

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.util.UUID

fun hydrometerStub(
    id: UUID = UUID.randomUUID(),
    reference: String = "022013",
    value: Long = 1,
    totalCounterValue: Long = 1,
    link: Link? = linkStub(),
) = Hydrometer(
    id = id,
    reference = reference,
    value = value,
    delivered = totalCounterValue,
    link = link,
)