package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.strategies.customer.CustomerStrategy
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
	val repository: CustomerRepository,
	val strategies: List<CustomerStrategy<Customer>>
): CustomerService() {

	override fun strategies(): List<CustomerStrategy<Customer>> = strategies
	override fun repository(): CustomerRepository = repository

	override fun findByName(name: String): Customer =  repository.findByName(name)
}

