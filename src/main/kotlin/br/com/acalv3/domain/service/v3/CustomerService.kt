package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.repository.v3.CustomerRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.CustomerSpec
import org.springframework.context.MessageSource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
	val repository: CustomerRepository,
	val messageSource: MessageSource,
): AppService<CustomerModel>(repository, repository) {

    override fun findByName(name: String): CustomerModel =
        repository.findByName(name)

	override fun prepareForSave(u: CustomerModel) {
		super.prepareForSave(u)

		when(u.document?.length) {
			11 -> u.personType = PersonTypeEnum.PHYSICAL
			else -> u.personType = PersonTypeEnum.LEGAL
		}

	}

	fun method(filter: FilterDTO<CustomerModel>){
		val customer = CustomerSpec(
			filter.model
		)

		customer.or(CustomerSpec(
			filter.model
		)
		)
	}

	override fun pageable(filter: FilterDTO<CustomerModel>)  =

		repository.findAll(

			CustomerSpec(
				filter.model
			),

			getPage(filter)
		)

	override fun validSave(u: CustomerModel) {

		if(repository.exists(Example.of(CustomerModel(document = u.document, deleted = false)))) {
			throw DuplicatedFieldException(
				messageSource.getMessage("error-on-save.customer", arrayOf(u.document), Locale.ENGLISH)
			)
		}
	}

	override fun validEdit(u: CustomerModel) {
		repository.findOne(Example.of(CustomerModel(document = u.document, deleted = false))).let {
			if (it.isPresent && it.get().id !== (u.id)) {
				validSave(u)
			}
		}
	}


}
