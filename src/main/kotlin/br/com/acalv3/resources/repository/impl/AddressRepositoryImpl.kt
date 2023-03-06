package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.resources.model.business.toAddress
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.resources.model.business.toAddressPage
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.AddressRepositoryJpa
import br.com.acalv3.resources.repository.specification.AddressSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AddressRepositoryImpl(
    private val repository: AddressRepositoryJpa,
) : AddressRepository, DefaultRepository {

    override fun getById(id: String): Address =
        repository.findByIdOrNull(UUID.fromString(id))?.toAddress() ?: throw NotFoundException()

    override fun save(address: Address): Address = repository.save(address.toAddressEntity()).toAddress()

    override fun update(address: Address): Address = repository.save(address.toAddressEntity()).toAddress()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun findByName(name: String): Address? = repository.findByName(name)?.toAddress()

    override fun paginate(pageRequest: AddressPage): Page<Address> =
        repository.findAll(
            AddressSpecification(pageRequest).getSpecification(),
            super.pageable(pageRequest)
        ).toAddressPage()

    override fun getAll(): List<Address> = repository.findAll().toAddress()

}
