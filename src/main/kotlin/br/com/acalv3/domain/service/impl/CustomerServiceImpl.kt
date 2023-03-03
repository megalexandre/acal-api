package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
@Service
class CustomerServiceImpl(
	val repository: CustomerRepository,
): CustomerService {
	override fun getById(id: String): Customer =
		repository.getById(id)

	override fun delete(id: String) = repository.delete(id)

	override fun save(customer: Customer): Customer = run{
		validSave(customer)
		repository.save(customer)
	}

	private fun validSave(customer: Customer) {
		if(repository.findByDocument(customer.document) != null){
			throw RuntimeException(
				"o documento ${customer.document} já está cadastrado para o usuário: ${customer.name}")
		}
	}

	override fun update(customer: Customer): Customer = run {
		validUpdate(customer)
		repository.update(customer)
	}

	private fun validUpdate(customer: Customer) {
		repository.findByDocument(customer.document)?.let {
			if(it.id != customer.id){
				throw RuntimeException(
					"o documento ${it.document} já está cadastrado para o usuário: ${it.name}")
			}
		}
	}

	override fun findByName(name: String): Customer =
        repository.findByName(name)

	override fun count(): Long = repository.count()

	override fun paginate(customerPage: CustomerPage): Page<Customer> =
		repository.paginate(customerPage)

}

