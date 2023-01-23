package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.repository.CustomerRepositoryJpa
import br.com.acalv3.resources.repository.impl.CustomerRepositoryImpl
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CustomerServiceImpl(
	val repository: CustomerRepository,
	val messageSource: MessageSource,
): CustomerService {
	override fun save(customer: Customer): Customer =
		repository.save(customer)
    override fun findByName(name: String): Customer =
        repository.findByName(name)


}
