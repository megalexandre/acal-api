package br.com.acalv3.domain.dto

import br.com.acalv3.domain.model.Link

class GenerateHydrometer(
    val addressName: String,
    val linkHydrometerPair: List<LinkHydrometerPair>
)

class LinkHydrometerPair(
    val link: Link,
    val lastConsumption: Long?
)



