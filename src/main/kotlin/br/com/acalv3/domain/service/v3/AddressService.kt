package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.repository.v3.AddressRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.AddressSpec
import org.springframework.context.MessageSource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressService(
	val repository: AddressRepository,
	val message: MessageSource,
): AppService<AddressModel>(repository, repository) {

    override fun findByName(name: String): AddressModel =
        repository.findByName(name)

	override fun pageable(filter: FilterDTO<AddressModel>)  =
		repository.findAll(

			AddressSpec(
				filter.model
			),

			getPage(filter)
		)

	override fun validSave(u: AddressModel) {
		val address = AddressModel(name = u.name,  addressType = AddressTypeModel(id = u.addressType?.id ), deletedBy = null)

		if(repository.exists(Example.of(address))) {
			throw DuplicatedFieldException(message.getMessage("error-on-save.address", arrayOf(u.name,u.addressType?.name ), Locale.ENGLISH))
		}
	}

	override fun validEdit(u: AddressModel) {
		val address = AddressModel(name = u.name,  addressType = u.addressType, deletedBy = null)

		repository.findOne(Example.of(address)).let {

			if (it.isPresent &&
				(!it.get().name.equals(u.name)) &&
				(it.get().addressType?.id != u.addressType?.id)
			) validSave(u)
		}

		super.validEdit(u)
	}


}