package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.repository.v3.ContractRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.ContractSpec
import org.springframework.context.MessageSource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ContractService(
	val repository: ContractRepository,
	val messageSource: MessageSource,
): AppService<ContractModel>(repository, repository) {

    override fun findByName(name: String): ContractModel =
        repository.findByName(name)


	override fun validSave(u: ContractModel) {

		if(repository.exists(Example.of(ContractModel(placeResidence = u.placeResidence, deleted = false)))) {
			throw DuplicatedFieldException(
				messageSource.getMessage("error-on-save.contract-duplicated-address", arrayOf(u.placeResidence), Locale.ENGLISH)
			)
		}

		super.validSave(u)
	}

	override fun prepareForSave(u: ContractModel) {
		super.prepareForSave(u)

		val year = LocalDateTime.now().year.toString().padStart(4,'0')
		val group = u.group?.id?.toString()?.padStart(4,'0')
		val count = (count() +1).toString().padStart(6,'0')

		u.name = "${year}${group}${count}"
	}

	override fun pageable(filter: FilterDTO<ContractModel>): Page<ContractModel> {
		return repository.findAll(

			ContractSpec(
				filter.model
			),

			getPage(filter)
		)
	}

}
