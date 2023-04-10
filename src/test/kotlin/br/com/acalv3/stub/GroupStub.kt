package br.com.acalv3.stub

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.enumeration.Category.FOUNDER
import br.com.acalv3.domain.model.Group
import java.math.BigDecimal
import java.math.BigDecimal.TEN
import java.util.UUID
import java.util.UUID.randomUUID

fun groupStub(
    id: UUID = randomUUID(),
    value: BigDecimal = TEN,
    category: Category = FOUNDER,
    name: String = "Residente",
) = Group(
    id = id,
    value = value,
    category = category,
    name = name,
)