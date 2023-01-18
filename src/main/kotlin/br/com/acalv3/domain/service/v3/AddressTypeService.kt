package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.repository.v3.AddressTypeRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.AddressTypeSpec
import org.springframework.context.MessageSource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressTypeService(
	val repository: AddressTypeRepository,
	val messageSource: MessageSource,
): AppService<AddressTypeModel>(repository, repository) {

    override fun findByName(name: String): AddressTypeModel =
        repository.findByName(name)

	override fun pageable(filter: FilterDTO<AddressTypeModel>)  =
		repository.findAll(

			AddressTypeSpec(
				filter.model
			),

			getPage(filter)
		)

	override fun validSave(u: AddressTypeModel) {

		if(repository.exists(Example.of(AddressTypeModel(name = u.name, deleted = false)))) {
			throw DuplicatedFieldException(
				messageSource.getMessage("error-on-save.address-type", arrayOf(u.name), Locale.ENGLISH)
			)
		}
	}

	override fun validEdit(u: AddressTypeModel) {
		repository.findOne(Example.of(AddressTypeModel(name = u.name, deleted = false))).let {
			if (it.isPresent && it.get().id !== (u.id)) {
				validSave(u)
			}
		}
	}
}