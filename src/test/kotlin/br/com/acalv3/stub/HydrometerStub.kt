package br.com.acalv3.stub

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import java.math.BigDecimal
import java.util.UUID

fun hydrometerStub(
    id: UUID = UUID.randomUUID(),
    reference: String = "022013",
    consumption: Long = 1000,
    costValue: BigDecimal = BigDecimal.TEN,
    link: Link? = linkStub(),
) = Hydrometer(
    id = id,
    reference = reference,
    link = link,
    costValue = costValue,
    consumption = consumption,
)
