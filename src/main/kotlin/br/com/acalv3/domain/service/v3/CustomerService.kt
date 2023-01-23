package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.repository.v3.CustomerRepository
import br.com.acalv3.resources.model.business.CustomerModel
import org.springframework.context.MessageSource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
	val repository: CustomerRepository,
	val messageSource: MessageSource,
) {

    fun findByName(name: String): CustomerModel =
        repository.findByName(name)

	fun prepareForSave(u: CustomerModel) {

		when(u.document?.length) {
			11 -> u.personType = PersonTypeEnum.PERSON
			else -> u.personType = PersonTypeEnum.LEGAL
		}

	}

	fun validSave(u: CustomerModel) {
		if(repository.exists(Example.of(CustomerModel(document = u.document)))) {
			throw DuplicatedFieldException(
				messageSource.getMessage("error-on-save.customer", arrayOf(u.document), Locale.ENGLISH)
			)
		}
	}

	fun validEdit(u: CustomerModel) {
		repository.findOne(Example.of(CustomerModel(document = u.document))).let {
			if (it.isPresent && it.get().id !== (u.id)) {
				validSave(u)
			}
		}
	}


}
