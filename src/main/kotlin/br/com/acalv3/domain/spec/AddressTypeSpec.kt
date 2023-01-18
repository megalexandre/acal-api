package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.spec.v3.AbstractSpec

class AddressTypeSpec (
	override val model: AddressTypeModel,
): AbstractSpec<AddressTypeModel>(model)