package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.strategies.customer.CustomerStrategy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
	val repository: CustomerRepository,
	val strategies: List<CustomerStrategy>
): CustomerService {

	override fun getById(id: String): Customer = repository.getById(id)

	override fun delete(id: String) =
		strategies.first{ it.action() === DELETE }.can(repository.getById(id)).let{
			repository.delete(id)
		}

	override fun save(customer: Customer): Customer =
		strategies.first{ it.action() === SAVE }.can(customer).let {
			repository.save(customer)
		}

	override fun update(customer: Customer): Customer =
		strategies.first{ it.action() === UPDATE }.can(customer).let {
			repository.update(customer)
		}

	override fun findByName(name: String): Customer =  repository.findByName(name)

	override fun count(): Long = repository.count()

	override fun paginate(customerPage: CustomerPage): Page<Customer> = repository.paginate(customerPage)

}

