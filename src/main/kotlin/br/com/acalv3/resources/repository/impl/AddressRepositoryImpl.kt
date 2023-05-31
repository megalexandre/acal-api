package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.resources.model.business.toAddress
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.resources.model.business.toAddressPage
import br.com.acalv3.resources.repository.interfaces.AddressRepositoryJpa
import br.com.acalv3.resources.repository.specification.AddressSpecification
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class AddressRepositoryImpl(
    private val repository: AddressRepositoryJpa,
) : AddressRepository {

    override fun getById(id: String): Address =
        repository.findByIdOrNull(UUID.fromString(id))?.toAddress() ?: throw NotFoundException()

    override fun save(type: Address): Address = repository.save(type.toAddressEntity()).toAddress()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun findByName(name: String): Address? = repository.findByName(name)?.toAddress()

    override fun paginate(page: AddressPage): Page<Address> =
        repository.findAll(
            AddressSpecification(page).getSpecification(),
            super.pageable(page)
        ).toAddressPage()

    override fun findAll(page: AddressPage): List<Address> =
        repository.findAll(AddressSpecification(page).getSpecification()).toAddress()

    override fun findAll(): List<Address> {
        TODO("Not yet implemented")
    }

    override fun count(): Long = repository.count()

    override fun getAll(): List<Address> = repository.findAll().toAddress()



}
