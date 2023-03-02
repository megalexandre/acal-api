package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.CustomerService
import javax.validation.ConstraintViolationException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
@Service
class CustomerServiceImpl(
	val repository: CustomerRepository,
): CustomerService {
	override fun getById(id: String): Customer =
		repository.getById(id)

	override fun delete(id: String) = repository.delete(id)

	override fun save(customer: Customer): Customer =
		try {
			repository.save(customer)
		} catch (ex: ConstraintViolationException){
			throw RuntimeException("documento já cadastrado")
		}

	override fun update(customer: Customer): Customer =
		repository.update(customer)

	override fun findByName(name: String): Customer =
        repository.findByName(name)

	override fun paginate(customerPage: CustomerPage): Page<Customer> =
		repository.paginate(customerPage)

}

