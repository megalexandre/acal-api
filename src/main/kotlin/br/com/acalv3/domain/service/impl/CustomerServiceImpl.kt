package br.com.acalv3.domain.service.impl

import br.com.acalv3.application.comunicate.model.request.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.CustomerService
import org.springframework.context.MessageSource
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
	val repository: CustomerRepository,
	val messageSource: MessageSource,
): CustomerService {
	override fun save(customer: Customer): Customer =
		repository.save(customer)

	override fun findByName(name: String): Customer =
        repository.findByName(name)

	override fun paginate(customerPageRequest: CustomerPageRequest): Page<Customer> =
		repository.paginate(customerPageRequest)

}
